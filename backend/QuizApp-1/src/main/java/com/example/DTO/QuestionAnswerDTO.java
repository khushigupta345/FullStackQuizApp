package com.example.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class QuestionAnswerDTO {
    private String questionText;
    private String correctOption;
    private String userAnswer;
}
