package com.example.DTO;





import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import jakarta.validation.constraints.NotNull;
@Getter
@Setter
@Data
public class QuizDetailsDTO {
    
    @NotNull(message = "QuizDTO cannot be null")
    private QuizDTO quizdto;

    @NotNull(message = "Questions list cannot be null")
    private List<QuestionDTO> questions;
}