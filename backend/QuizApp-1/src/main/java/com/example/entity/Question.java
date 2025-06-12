package com.example.entity;

import com.example.DTO.QuestionDTO;
import com.example.DTO.QuizDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String questionText;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String correctOption;
	@ManyToOne
	@JoinColumn
	private Quiz quiz;
	
	
public QuestionDTO getDto() {
		
	 QuestionDTO t=new  QuestionDTO();
		t.setId(id);
		t.setQuestionText(questionText);
t.setOptionA(optionA);
t.setOptionB(optionB);
t.setOptionC(optionC);
t.setOptionD(optionD);
t.setCorrectOption(correctOption);
		return t;
		
	}
}
