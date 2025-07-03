
package com.example.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;
@Getter
@Setter
@Data
public class SubmitQuizDTO {

    @NotNull(message = "Quiz ID cannot be null")
    @Positive(message = "Quiz ID must be positive")
    private Long quizId;

    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be positive")
    private Long userId;

    @NotNull(message = "Response list cannot be null")
    @Size(min = 1, message = "At least one response must be provided")
    private List<QuestionResponse> response;
}
