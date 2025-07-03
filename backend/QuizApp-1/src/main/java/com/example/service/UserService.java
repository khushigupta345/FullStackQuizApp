package com.example.service;

import com.example.entity.User;

public interface UserService {
	public Boolean  hasUserWithEmail(String email) ;
		
		User login(User user);
		

	public User createUser(User user);

	}

