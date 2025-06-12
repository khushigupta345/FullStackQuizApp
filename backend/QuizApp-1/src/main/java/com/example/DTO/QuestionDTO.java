//package com.example.DTO;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Data
//
//@Getter
//@Setter
//public class QuestionDTO {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	
//	private Long id;
//	private String questionText;
//	private String optionA;
//	private String optionB;
//	private String optionC;
//	private String optionD;
//	private String correctOption;
//	
//	
//	
//	
//}
package com.example.DTO;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class QuestionDTO {

    private Long id;

    @SuppressWarnings("deprecation")
	@NotNull(message = "Question text cannot be null")
    @NotEmpty(message = "Question text cannot be empty")
    private String questionText;

    @NotNull(message = "Option A cannot be null")
    @NotEmpty(message = "Option A cannot be empty")
    private String optionA;

    @NotNull(message = "Option B cannot be null")
    @NotEmpty(message = "Option B cannot be empty")
    private String optionB;

    @NotNull(message = "Option C cannot be null")
    @NotEmpty(message = "Option C cannot be empty")
    private String optionC;

    @NotNull(message = "Option D cannot be null")
    @NotEmpty(message = "Option D cannot be empty")
    private String optionD;

    @NotNull(message = "Correct option cannot be null")
   
    private String correctOption;
}