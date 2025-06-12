package com.example.entity;

import java.util.List;

import com.example.DTO.QuizDTO;
import com.example.DTO.QuizResultDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizResult {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
	
	private int  totalQuestion;
	private int correctAnswers;
	private double percentage;
@ManyToOne
@JoinColumn(name="quiz_id")
	private Quiz quiz;
@ManyToOne
@JoinColumn(name="user_id")
	private User user;
	
public QuizResultDTO getDto() {
	
	QuizResultDTO t=new QuizResultDTO();
	t.setId(id);
	
	t.setTotalQuestion(totalQuestion);
	t.setPercentage(percentage);
t.setCorrectAnswers(correctAnswers);
t.setQuizId(quiz.getId());
//t.setUserName(user.getName());
t.setUserId(user.getId());
	return t;
	
}
	
	
	
}
