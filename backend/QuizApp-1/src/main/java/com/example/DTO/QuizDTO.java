package com.example.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
@Data
@Getter
@Setter


	public class QuizDTO {
	    
	    private Long id;

	    @NotNull(message = "Title cannot be null")
	    @NotEmpty(message = "Title cannot be empty")
	    private String title;

	    @NotNull(message = "Description cannot be null")
	    @NotEmpty(message = "Description cannot be empty")
	    private String description;

	    @NotNull(message = "Time cannot be null")
	    @Positive(message = "Time must be positive")
	    private Long time;
	}
	


