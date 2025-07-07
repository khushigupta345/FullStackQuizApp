package com.example.DTO;
import com.example.DTO.QuestionAnswerDTO;
import java.util.List;


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

    private double percentage;

    private Long quizId;

private List<QuestionAnswerDTO> questionAnswers;
   
    private Long userId;
}



