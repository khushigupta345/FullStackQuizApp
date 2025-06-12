package com.example.DTO;

import java.util.List;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//@Data
//@Getter
//@Setter
//public class QuizResultDTO{
//private Long id;
//	
//	private int  totalQuestion;
//	private int correctAnswers;
//	private double percentage;
//	private String quizName;
//	
//	private String userName;
//}


import lombok.Data;
import lombok.Getter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Setter;
@Getter
@Setter
@Data
public class QuizResultDTO {
    
    private Long id;

    @NotNull(message = "Total questions cannot be null")
    @PositiveOrZero(message = "Total questions cannot be zero or negative")
    private int totalQuestion;

    @NotNull(message = "Correct answers cannot be null")
    @PositiveOrZero(message = "Correct answers cannot be negative")
    private int correctAnswers;

//    @Min(value = 0, message = "Percentage cannot be less than 0")
//    @Max(value = 100, message = "Percentage cannot be more than 100")
    private double percentage;

//    @NotNull(message = "Quiz name cannot be null")
//    @NotEmpty(message = "Quiz name cannot be empty")
    private Long quizId;

//    @NotNull(message = "User name cannot be null")
//    @NotEmpty(message = "User name cannot be empty")
//    private String userName;
   
    private Long userId;
}



