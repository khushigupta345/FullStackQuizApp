package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

import jakarta.validation.Valid;



@RestController
//@RequestMapping("/api/auth")

@CrossOrigin(origins ="https://full-stack-quiz-app-khushis-projects-d7056416.vercel.app")
public class UserController {
	@Autowired
	private UserService userservice;
	@PostMapping("/signup")
	public ResponseEntity<?>signUpUser(@Valid@RequestBody User user){
		if(userservice.hasUserWithEmail(user.getEmail())) {
			return new  ResponseEntity<>("user with this email allready exist",HttpStatus.NOT_ACCEPTABLE);
			
		}
		User createdUser =userservice.createUser(user);
		if(createdUser==null) {
			return new ResponseEntity<>("user not created",HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(createdUser,HttpStatus.OK);
		
	}

	
}
