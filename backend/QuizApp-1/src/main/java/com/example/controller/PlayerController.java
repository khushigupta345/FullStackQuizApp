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
@RequestMapping("/api/user")
@CrossOrigin(origins ="https://full-stack-quiz-app-rho.vercel.app")
@PreAuthorize("hasAuthority('USER')")
public class PlayerController {
@Autowired
public QuizService q;


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
@PostMapping("/submittest")
public ResponseEntity<?> submitQuiz(@Valid@RequestBody SubmitQuizDTO dto){
	try {
	return new ResponseEntity<>(q.submitQuiz(dto),HttpStatus.OK);
	}catch(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
 // User can only see their own result
@GetMapping("/test-result/{userId}")

public ResponseEntity<?> getallquizresult(@PathVariable Long userId){
	try {
	return new ResponseEntity<>(q.getallresultbyid(userId),HttpStatus.OK);
	}catch(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}

}


