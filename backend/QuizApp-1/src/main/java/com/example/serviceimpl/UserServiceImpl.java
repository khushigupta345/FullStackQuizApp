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
	// UserServiceImpl.java @Service public class UserServiceImpl implements UserService {


//	@Autowired
//	private PasswordEncoder passwordEncoder;
////@Autowired
////private UserRepository u;
////@PostConstruct
////private void createUserAdmin() {
////	User optionalUser =u.findByRole(UserRole.ADMIN);
////	if(optionalUser==null) {
////	User user=new User();
////	user.setName("ADMIN");
////	user.setEmail("admin@gmail.com");
////	user.setRole("Role_ADMIN");
////	user.setPassword("admin");
////	
////	u.save(user);
////	
////	}
////	
////}

//	@Override
//	public User createUserAdmin(User user) {
//	    Optional<User> optionalUser = Optional.ofNullable(u.findByRole(UserRole.ADMIN));
//	    if (!optionalUser.isPresent()) {
//	        user.setRole(UserRole.ADMIN);
//	        user.setPassword(passwordEncoder.encode(user.getPassword()));
//	        return u.save(user);
//	    }
//	    return optionalUser.get();
//	}
////
//	@Override
//	public User login(User user) {
//	    Optional<User> optionalUser = u.findByEmail(user.getEmail());
//	    if (optionalUser.isPresent() && passwordEncoder.matches(user.getPassword(), optionalUser.get().getPassword())) {
//	        return optionalUser.get();
//	    }
//	    throw new RuntimeException("Invalid credentials");
//	}
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
	
////	@Override
////	public User login(User user) {
////		Optional<User>optionaluser=u.findByEmail(user.getEmail());
////		System.out.println(optionaluser.isPresent() && user.getPassword().equals(optionaluser.get().getPassword()));
////		
////		if(optionaluser.isPresent() && user.getPassword().equals(optionaluser.get().getPassword())) {
////			return optionaluser.get();
////		}
////		return null;
////	}
//}
//
////package com.example.serviceimpl;
////
////import java.util.Optional;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import com.example.entity.User;
////import com.example.enums.UserRole;
////import com.example.repository.UserRepository;
////import com.example.service.UserService;
////
////import jakarta.annotation.PostConstruct;
////@Service
////public class UserServiceImpl implements UserService {
////	@Autowired
////	private UserRepository u;
////	@PostConstruct
////	private void createUserAdmin() {
////		User optionalUser =u.findByRole(UserRole.ADMIN);
////		if(optionalUser==null) {
////		User user=new User();
////		user.setName("ADMIN");
////		user.setEmail("admin@gmail.com");
////		user.setRole("Role_ADMIN");
////		user.setPassword("admin");
////		
////		u.save(user);
////		
////		}
////		
////	}
////	@Override
////	public Boolean  hasUserWithEmail(String email) {
////		return u.findFirstByEmail(email)!=null;
////		
////	}
////	@Override
////	public User createUser(User user) {
////		user.setRole(UserRole.USER);
////		return u.save(user);
////		
////	}
////	
////	@Override
////	public User login(User user) {
////		Optional<User>optionaluser=u.findByEmail(user.getEmail());
////		System.out.println(optionaluser.isPresent() && user.getPassword().equals(optionaluser.get().getPassword()));
////		
////		if(optionaluser.isPresent() && user.getPassword().equals(optionaluser.get().getPassword())) {
////			return optionaluser.get();
////		}
////		return null;
////	}
////}
