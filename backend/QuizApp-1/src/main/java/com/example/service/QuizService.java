package com.example.service;

import java.util.List;

import com.example.DTO.QuestionDTO;
import com.example.DTO.QuizDTO;
import com.example.DTO.QuizDetailsDTO;
import com.example.DTO.QuizResultDTO;
import com.example.DTO.SubmitQuizDTO;
import com.example.entity.Quiz;

public interface QuizService {


QuizDTO createTest(QuizDTO dto);
public QuestionDTO addquestionquiz(QuestionDTO dto) ;
List<QuizDTO> getAllQuiz();
 QuizDetailsDTO getallqustionbyquiz(Long id);

QuizResultDTO submitQuiz(SubmitQuizDTO dto);
List<QuizResultDTO>getallquizresult();

List<QuizResultDTO> getallresultbyid(Long userId);
boolean  deletequizbyid(Long id);
QuizDTO getquizbyid(Long id);
boolean updateQuiz(Long id, QuizDTO updatedQuiz);
}
