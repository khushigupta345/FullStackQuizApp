package com.example.entity;

import java.util.List;

import com.example.DTO.QuizDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Quiz {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
	
	private String title;
	private String description;
	private Long time;
	@OneToMany(mappedBy = "quiz" ,cascade = CascadeType.ALL)
	private List<Question> questions;
	@OneToMany(mappedBy = "quiz" ,cascade = CascadeType.ALL)
	private List<QuizResult> quizresult;
	
	public QuizDTO getDto() {
		
		QuizDTO t=new QuizDTO();
		t.setId(id);
		t.setDescription(description);
t.setTime(time);
t.setTitle(title);
		return t;
		
	}
	
	
	
	
}
