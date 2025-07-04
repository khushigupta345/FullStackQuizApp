package com.example.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.QuestionDTO;
import com.example.DTO.QuestionResponse;
import com.example.DTO.QuizDTO;
import com.example.DTO.QuizDetailsDTO;
import com.example.DTO.QuizResultDTO;
import com.example.DTO.SubmitQuizDTO;
import com.example.Exception.QuestionNotFoundException;
import com.example.Exception.QuizNotFoundException;
import com.example.Exception.UserNotFoundException;
import com.example.entity.Question;
import com.example.entity.Quiz;
import com.example.entity.QuizResult;
import com.example.entity.User;
import com.example.repository.QuestionRepository;
import com.example.repository.QuizRepository;
import com.example.repository.QuizResultRepository;
import com.example.repository.UserRepository;
import com.example.service.QuizService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuizResultRepository resultRepository;
	@Autowired
	private EntityManager entityManager;
@Autowired
private QuizRepository qv;
@Autowired
private QuestionRepository qestion;
@Override
public QuizDTO createTest(QuizDTO dto) {
Quiz q=new Quiz();
q.setTitle(dto.getTitle());
q.setDescription(dto.getDescription());
q.setTime(dto.getTime());

	return qv.save(q).getDto();
}

@Override
public QuestionDTO addquestionquiz(QuestionDTO dto) {
	Optional<Quiz> o=qv.findById(dto.getId());
	if(o.isPresent()) {
		Question question =new 	Question();
		question.setQuiz(o.get());
		question.setQuestionText(dto.getQuestionText());
		question.setOptionA(dto.getOptionA());
		question.setOptionB(dto.getOptionB());
		question.setOptionC(dto.getOptionC());
		question.setOptionD(dto.getOptionD());
		question.setCorrectOption(dto.getCorrectOption());
		return qestion.save(question).getDto();
	}
	
	throw  new QuizNotFoundException("Quiz not found");
	
}
@Override
public List<QuizDTO> getAllQuiz(){
	
	return qv.findAll().stream().peek(test->test.setTime(test.getQuestions().size()*test.getTime())).collect(Collectors.toList()).stream().map(Quiz::getDto).collect(Collectors.toList());
	
}

public QuizDetailsDTO getallqustionbyquiz(Long id) {
	Optional<Quiz> o=qv.findById(id);
	QuizDetailsDTO p=new QuizDetailsDTO();
	if(o.isPresent()) {
		QuizDTO h=new QuizDTO();
		h.setTime(o.get().getTime()*o.get().getQuestions().size());
		p.setQuizdto(h);
		p.setQuestions(o.get().getQuestions().stream().map(Question::getDto).toList());
		return p;
	}
	return p;
}

public QuizResultDTO submitQuiz(SubmitQuizDTO request) {
	Quiz quiz=qv.findById(request.getQuizId()).orElseThrow(()->new QuizNotFoundException("quiz not found"));
	User user=userRepository.findById(request.getUserId()).orElseThrow(()->new UserNotFoundException("user not found"));
	
	int correctAnswer=0;
	for(QuestionResponse response:request.getResponse()) {
		
		Question question=qestion.findById(response.getQuestionId()).orElseThrow(()->new QuestionNotFoundException("question not found"));
		if(question.getCorrectOption().equals(response.getSelectedOption())) {
			correctAnswer++;
			System.out.println(correctAnswer);
			
		}
		
	}
	int totalQuestion =quiz.getQuestions().size();


	QuizResult t=new QuizResult();
	t.setQuiz(quiz);
	t.setUser(user);
	t.setTotalQuestion(totalQuestion);
	t.setCorrectAnswers(correctAnswer);
	t.setPercentage(Double.parseDouble(String.format("%.3f",((double)correctAnswer/totalQuestion)*100)));
	
	return resultRepository.save(t).getDto();
	
	
}
public List<QuizResultDTO>getallquizresult(){
	return resultRepository.findAll().stream().map(QuizResult::getDto).toList();
	
	
}
public  List<QuizResultDTO> getallresultbyid(Long userId){
	
	return resultRepository.findAllByUserId(userId).stream().map(QuizResult::getDto).toList();
	
}


@Override
@Transactional
public boolean deletequizbyid(Long id) {
    if (qv.existsById(id)) {
        // All related questions will be deleted because of cascade + orphanRemoval
        qv.deleteById(id);
        return true;
    }
    return false;
}
	
public boolean updateQuiz(Long id, QuizDTO updatedQuizDto) {
    if (updatedQuizDto == null) return false; 

    return qv.findById(id).map(existingQuiz -> {
        existingQuiz.setTitle(updatedQuizDto.getTitle());
        existingQuiz.setDescription(updatedQuizDto.getDescription());
        qv.save(existingQuiz);
        return true;
    }).orElse(false);
}
public QuizDTO getquizbyid(Long id)
{
	Optional<Quiz>q= qv.findById(id);
	return q.map(Quiz::getDto).orElseThrow(()->new QuizNotFoundException("quiz not found"));
	
	
}


}
