package com.example.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.enums.UserRole;
import com.example.repository.UserRepository;
import com.example.service.UserService;

import jakarta.annotation.PostConstruct;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository u;

	@Override
	public User login(User user) {
	    Optional<User> optionalUser = u.findByEmail(user.getEmail());
	    if (optionalUser.isPresent() && user.getPassword().equals(optionalUser.get().getPassword())) {
	        return optionalUser.get();
	    }
	    throw new RuntimeException("Invalid credentials");
	}

//	
	@Override
	public Boolean  hasUserWithEmail(String email) {
		return u.findFirstByEmail(email)!=null;
		
	}
	@Override
	public User createUser(User user) {
		user.setRole(UserRole.USER);
		return u.save(user);
	}	
	}
	
