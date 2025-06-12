//package com.example.DTO;
//
//import java.util.List;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//@Data
//@Getter
//@Setter
//
//public class QuestionResponse {
//	@NotNull(message = "Question ID cannot be null")
//	@Positive(message = "Question ID must be positive")
//	private Long questionId;
//
//	@NotNull(message = "Selected option cannot be null")
//	@Pattern(regexp = "^[A-D]$", message = "Selected option must be A, B, C, or D")
//	private String selectedOption;
//
//}
package com.example.DTO;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class QuestionResponse {

    @NotNull(message = "Question ID cannot be null")
    @Positive(message = "Question ID must be positive")
    private Long questionId;

    @NotNull(message = "Selected option cannot be null")
    
    private String selectedOption;
}
