package com.example.controller;

import java.util.HashMap;
import java.util.Map;

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
public ResponseEntity<Map<String, String>> deleteQuiz(@PathVariable("id") Long id) {
    Map<String, String> response = new HashMap<>();
    try {
        boolean isDeleted = q.deletequizbyid(id);
        if (isDeleted) {
            response.put("message", "Quiz deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Quiz not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    } catch (Exception e) {
        response.put("error", "Server error: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

@GetMapping("quiz/{id}")
public ResponseEntity<QuizDTO > getquizbyid(@PathVariable Long id){
	QuizDTO quizdto=q.getquizbyid(id);
	return   ResponseEntity.ok(quizdto);
	
}

@PutMapping("/quizzes/{id}")
public ResponseEntity<Map<String, String>> updateQuiz(@PathVariable Long id, @Valid @RequestBody QuizDTO updatedQuiz) {
    Map<String, String> response = new HashMap<>();
    
    if (q.updateQuiz(id, updatedQuiz)) {
        response.put("message", "Quiz updated successfully");
        return ResponseEntity.ok(response);
    } else {
        response.put("error", "Quiz not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
}
