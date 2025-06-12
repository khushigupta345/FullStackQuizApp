package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.service.QuizService;

import jakarta.validation.Valid;

import com.example.DTO.QuestionDTO;
import com.example.DTO.QuizDTO;
import com.example.DTO.SubmitQuizDTO;
@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins ="http://localhost:4200")
@PreAuthorize("hasAuthority('ADMIN')") // Only admin can
public class GameMasterController {
	@Autowired
public QuizService q;

@PostMapping("/test")

public ResponseEntity<?> createTest(@Valid @RequestBody QuizDTO dto){
	try {
	return new ResponseEntity<>(q.createTest(dto),HttpStatus.OK);
	}catch(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
	}
}

@PostMapping("/question")
public ResponseEntity<?> addQuestiont(@Valid@RequestBody QuestionDTO dto){
	try {
	return new ResponseEntity<>(q.addquestionquiz(dto),HttpStatus.CREATED);
	}catch(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
@GetMapping()
public ResponseEntity<?> getquiz(){
	try {
	return new ResponseEntity<>(q.getAllQuiz(),HttpStatus.OK);
	}catch(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
@GetMapping("/{id}")
public ResponseEntity<?> getallquestion(@PathVariable Long id){
	try {
	return new ResponseEntity<>(q.getallqustionbyquiz(id),HttpStatus.OK);
	
	}catch(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}

 // Admin can see all results
@GetMapping("/test-result")
public ResponseEntity<?> getallquizresult(){
	try {
	return new ResponseEntity<>(q.getallquizresult(),HttpStatus.OK);
	}catch(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}





@DeleteMapping("delete/{id}")
public ResponseEntity<String > DeleteQuiz(@PathVariable Long id){
	try {
		boolean isdeleted=q.deletequizbyid(id);
	return  ResponseEntity.ok("delete successfully");
	}catch(Exception e){
	return	ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something wentn wrong");
		
	}
	
}

@GetMapping("quiz/{id}")
public ResponseEntity<QuizDTO > getquizbyid(@PathVariable Long id){
	QuizDTO quizdto=q.getquizbyid(id);
	return   ResponseEntity.ok(quizdto);
	
}

@PutMapping("/quizzes/{id}")
public ResponseEntity<String> updateQuiz(@PathVariable Long id,@Valid @RequestBody QuizDTO updatedQuiz) {
  if (q.updateQuiz(id, updatedQuiz)) {
      return ResponseEntity.ok("Quiz updated successfully");
  } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
  }
}
}

//package com.example.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.http.HttpStatus;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import com.example.service.QuizService;
//
//import jakarta.validation.Valid;
//
//import com.example.DTO.QuestionDTO;
//import com.example.DTO.QuizDTO;
//import com.example.DTO.SubmitQuizDTO;
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins ="http://localhost:4200")
//public class QuizController {
//@Autowired
//public QuizService q;
//@PreAuthorize("hasAuthority('ADMIN')") // Only admin can
//@PostMapping("/test")
//
//public ResponseEntity<?> createTest(@Valid @RequestBody QuizDTO dto){
//	try {
//	return new ResponseEntity<>(q.createTest(dto),HttpStatus.OK);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
//	}
//}
//@PreAuthorize("hasAuthority('ADMIN')") // Only admin can add questions
//@PostMapping("/question")
//public ResponseEntity<?> addQuestiont(@Valid@RequestBody QuestionDTO dto){
//	try {
//	return new ResponseEntity<>(q.addquestionquiz(dto),HttpStatus.CREATED);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@GetMapping()
//public ResponseEntity<?> getquiz(){
//	try {
//	return new ResponseEntity<>(q.getAllQuiz(),HttpStatus.OK);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@GetMapping("/{id}")
//public ResponseEntity<?> getallquestion(@PathVariable Long id){
//	try {
//	return new ResponseEntity<>(q.getallqustionbyquiz(id),HttpStatus.OK);
//	
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@PostMapping("/submittest")
//public ResponseEntity<?> submitQuiz(@Valid@RequestBody SubmitQuizDTO dto){
//	try {
//	return new ResponseEntity<>(q.submitQuiz(dto),HttpStatus.OK);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@PreAuthorize("hasAuthority('ADMIN')") // Admin can see all results
//@GetMapping("/test-result")
//public ResponseEntity<?> getallquizresult(){
//	try {
//	return new ResponseEntity<>(q.getallquizresult(),HttpStatus.OK);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@PreAuthorize("hasAuthority('USER')") // User can only see their own result
//@GetMapping("/test-result/{userId}")
//
//public ResponseEntity<?> getallquizresult(@PathVariable Long userId){
//	try {
//	return new ResponseEntity<>(q.getallresultbyid(userId),HttpStatus.OK);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@PreAuthorize("hasAuthority('ADMIN')") 
//@DeleteMapping("delete/{id}")
//public ResponseEntity<String > DeleteQuiz(@PathVariable Long id){
//	try {
//		boolean isdeleted=q.deletequizbyid(id);
//	return  ResponseEntity.ok("delete successfully");
//	}catch(Exception e){
//	return	ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something wentn wrong");
//		
//	}
//	
//}
//
//@PreAuthorize("hasAuthority('ADMIN')") 
//@GetMapping("quiz/{id}")
//public ResponseEntity<QuizDTO > getquizbyid(@PathVariable Long id){
//	QuizDTO quizdto=q.getquizbyid(id);
//	return   ResponseEntity.ok(quizdto);
//	
//}
//
//@PreAuthorize("hasAuthority('ADMIN')") 
//@PutMapping("/quizzes/{id}")
//public ResponseEntity<String> updateQuiz(@PathVariable Long id,@Valid @RequestBody QuizDTO updatedQuiz) {
//  if (q.updateQuiz(id, updatedQuiz)) {
//      return ResponseEntity.ok("Quiz updated successfully");
//  } else {
//      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
//  }
//}
//}


//package com.example.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import com.example.service.QuizService;
//
//import jakarta.validation.Valid;
//
//import com.example.DTO.QuestionDTO;
//import com.example.DTO.QuizDTO;
//import com.example.DTO.SubmitQuizDTO;
//import com.example.entity.Quiz;
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins ="http://localhost:4200")
//public class QuizController {
//@Autowired
//public QuizService q;
//@PostMapping("/test")
//public ResponseEntity<?> createTest(@Valid@RequestBody QuizDTO dto){
//	try {
//	return new ResponseEntity<>(q.createTest(dto),HttpStatus.OK);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
//	}
//}
//@PostMapping("/question")
//public ResponseEntity<?> addQuestiont(@Valid@RequestBody QuestionDTO dto){
//	try {
//	return new ResponseEntity<>(q.addquestionquiz(dto),HttpStatus.CREATED);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@GetMapping()
//public ResponseEntity<?> getquiz(){
//	try {
//	return new ResponseEntity<>(q.getAllQuiz(),HttpStatus.OK);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@GetMapping("/{id}")
//public ResponseEntity<?> getallquestion(@PathVariable Long id){
//	try {
//	return new ResponseEntity<>(q.getallqustionbyquiz(id),HttpStatus.OK);
//	
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@PostMapping("/submittest")
//public ResponseEntity<?> submitQuiz(@RequestBody SubmitQuizDTO dto){
//	try {
//	return new ResponseEntity<>(q.submitQuiz(dto),HttpStatus.OK);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@GetMapping("/test-result")
//public ResponseEntity<?> getallquizresult(){
//	try {
//	return new ResponseEntity<>(q.getallquizresult(),HttpStatus.OK);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@GetMapping("/test-result/{userId}")
//public ResponseEntity<?> getallquizresult(@PathVariable Long userId){
//	try {
//	return new ResponseEntity<>(q.getallresultbyid(userId),HttpStatus.OK);
//	}catch(Exception e){
//		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//	}
//}
//@DeleteMapping("delete/{id}")
//public ResponseEntity<String > DeleteQuiz(@PathVariable Long id){
//	try {
//		boolean isdeleted=q.deletequizbyid(id);
//	return  ResponseEntity.ok("delete successfully");
//	}catch(Exception e){
//	return	ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something wentn wrong");
//		
//	}
//	
//}
//
//@GetMapping("quiz/{id}")
//public ResponseEntity<QuizDTO > getquizbyid(@PathVariable Long id){
//	QuizDTO quizdto=q.getquizbyid(id);
//	return   ResponseEntity.ok(quizdto);
//	
//}
//
//@PutMapping("/quizzes/{id}")
//public ResponseEntity<String> updateQuiz(@PathVariable Long id,@Valid @RequestBody QuizDTO updatedQuiz) {
//    if (q.updateQuiz(id, updatedQuiz)) {
//        return ResponseEntity.ok("Quiz updated successfully");
//    } else {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
//    }
//}
//}
