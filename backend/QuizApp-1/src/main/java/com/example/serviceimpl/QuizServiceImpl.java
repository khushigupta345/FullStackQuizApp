package com.example.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.*;
import com.example.Exception.QuestionNotFoundException;
import com.example.Exception.QuizNotFoundException;
import com.example.Exception.UserNotFoundException;
import com.example.entity.*;
import com.example.repository.*;
import com.example.service.QuizService;

import jakarta.transaction.Transactional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository qv;

    @Autowired
    private QuestionRepository qestion;

    @Autowired
    private QuizResultRepository resultRepository;

    @Autowired
    private QuestionAnswerRepository qaRepo;

    @Override
    public QuizDTO createTest(QuizDTO dto) {
        Quiz q = new Quiz();
        q.setTitle(dto.getTitle());
        q.setDescription(dto.getDescription());
        q.setTime(dto.getTime());
        return qv.save(q).getDto();
    }

    @Override
    public QuestionDTO addquestionquiz(QuestionDTO dto) {
        Optional<Quiz> o = qv.findById(dto.getId());
        if (o.isPresent()) {
            Question question = new Question();
            question.setQuiz(o.get());
            question.setQuestionText(dto.getQuestionText());
            question.setOptionA(dto.getOptionA());
            question.setOptionB(dto.getOptionB());
            question.setOptionC(dto.getOptionC());
            question.setOptionD(dto.getOptionD());
            question.setCorrectOption(dto.getCorrectOption());
            return qestion.save(question).getDto();
        }
        throw new QuizNotFoundException("Quiz not found");
    }

    @Override
    public List<QuizDTO> getAllQuiz() {
        return qv.findAll().stream()
                .peek(test -> test.setTime(test.getQuestions().size() * test.getTime()))
                .map(Quiz::getDto)
                .collect(Collectors.toList());
    }

    @Override
    public QuizDetailsDTO getallqustionbyquiz(Long id) {
        Optional<Quiz> o = qv.findById(id);
        QuizDetailsDTO p = new QuizDetailsDTO();
        if (o.isPresent()) {
            QuizDTO h = new QuizDTO();
            h.setTime(o.get().getTime() * o.get().getQuestions().size());
            p.setQuizdto(h);
            p.setQuestions(o.get().getQuestions().stream().map(Question::getDto).toList());
            return p;
        }
        return p;
    }

    @Override
    public QuizResultDTO submitQuiz(SubmitQuizDTO request) {
        Quiz quiz = qv.findById(request.getQuizId())
                .orElseThrow(() -> new QuizNotFoundException("quiz not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("user not found"));

        QuizResult result = new QuizResult();
        result.setQuiz(quiz);
        result.setUser(user);
        result.setTotalQuestion(quiz.getQuestions().size());

        List<QuestionAnswer> answers = new ArrayList<>();
        int correctAnswer = 0;

        for (QuestionResponse response : request.getResponse()) {
            Question question = qestion.findById(response.getQuestionId())
                    .orElseThrow(() -> new QuestionNotFoundException("question not found"));

            if (question.getCorrectOption().equalsIgnoreCase(response.getSelectedOption())) {
                correctAnswer++;
            }

            QuestionAnswer qa = new QuestionAnswer();
            qa.setQuestionText(question.getQuestionText());
            qa.setCorrectOption(question.getCorrectOption());
            qa.setUserAnswer(response.getSelectedOption());
            qa.setQuizResult(result); // Bidirectional mapping

            answers.add(qa);
        }

        result.setCorrectAnswers(correctAnswer);
        result.setPercentage(Double.parseDouble(String.format("%.2f",
                ((double) correctAnswer / quiz.getQuestions().size()) * 100)));

        result.setQuestionAnswers(answers);

        QuizResult saved = resultRepository.save(result); // Cascade saves all QuestionAnswer
        return saved.getDto();
    }

    @Override
    public List<QuizResultDTO> getallquizresult() {
        return resultRepository.findAll().stream()
                .map(QuizResult::getDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuizResultDTO> getallresultbyid(Long userId) {
        return resultRepository.findAllByUserId(userId).stream()
                .map(QuizResult::getDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deletequizbyid(Long id) {
        if (qv.existsById(id)) {
            qv.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateQuiz(Long id, QuizDTO updatedQuizDto) {
        if (updatedQuizDto == null) return false;

        return qv.findById(id).map(existingQuiz -> {
            existingQuiz.setTitle(updatedQuizDto.getTitle());
            existingQuiz.setDescription(updatedQuizDto.getDescription());
            qv.save(existingQuiz);
            return true;
        }).orElse(false);
    }

    @Override
    public QuizDTO getquizbyid(Long id) {
        return qv.findById(id)
                .map(Quiz::getDto)
                .orElseThrow(() -> new QuizNotFoundException("quiz not found"));
    }
}
