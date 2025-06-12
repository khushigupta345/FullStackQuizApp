//package com.example.controller;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.config.JwtService;
//import com.example.entity.User;
//import com.example.repository.UserRepository;
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/oauth2/success")
//    public ResponseEntity<?> getTokenAfterOAuth2Login(@RequestParam String email) {
//        Optional<User> userOpt = userRepository.findByEmail(email);
//
//        if (userOpt.isPresent()) {
//            User user = userOpt.get();
//            String token = jwtService.generateToken(user.getEmail(), user.getRole().toString());
//
//            return ResponseEntity.ok(Map.of("token", token));
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not registered");
//        }
//    }
//}
//package com.example.controller;
//
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.config.JwtUtil;
////import com.example.config.JwtUtil;
//import com.example.entity.User;
//import com.example.enums.UserRole;
//import com.example.repository.UserRepository;
//
//
//@RestController
//@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:4200")
//public class AuthController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    // ✅ User Registration
//    @PostMapping("/signup")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(UserRole.USER);
//        
//        userRepository.save(user);
//        return ResponseEntity.ok("User registered successfully");
//    }
//
////    // ✅ User Login (JWT Generate)
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
//
//        if (dbUser.isPresent() && passwordEncoder.matches(user.getPassword(), dbUser.get().getPassword())) {
//            String token = jwtUtil.generateToken(dbUser.get().getEmail(), dbUser.get().getRole().toString());
//            return ResponseEntity.ok(Collections.singletonMap("token", token));
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }}
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
//
//        if (dbUser.isPresent() && passwordEncoder.matches(user.getPassword(), dbUser.get().getPassword())) {
//            String token = jwtUtil.generateToken(dbUser.get().getEmail(), dbUser.get().getRole().toString());
//            
//            Map<String, Object> response = new HashMap<>();
//            response.put("token", token);
//            response.put("role", dbUser.get().getRole().toString());
//
//            return ResponseEntity.ok(response);
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }
//}
////import java.util.Collections;
////import java.util.Date;
////import java.util.Optional;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
////import org.springframework.web.bind.annotation.CrossOrigin;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestBody;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RestController;
////
////import com.example.entity.User;
////import com.example.repository.UserRepository;
////import com.example.service.UserService;
////
////import io.jsonwebtoken.Jwts;
////import io.jsonwebtoken.security.Keys;
////@RestController
////@RequestMapping("/auth")
////public class AuthController {
////    @Autowired
////    private UserRepository userRepository;
////    @Autowired
////    private PasswordEncoder passwordEncoder;
////
////    @PostMapping("/signup")
////    public ResponseEntity<String> registerUser(@RequestBody User user) {
////        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
////        }
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
////        user.setRole("ROLE_USER");
////        userRepository.save(user);
////        
////        return ResponseEntity.ok("User registered successfully");
////        
////    }
////
////    
////    
////    @PostMapping("/login")
////    public ResponseEntity<?> loginUser(@RequestBody User user) {
////        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
////        if (dbUser.isPresent() && passwordEncoder.matches(user.getPassword(), dbUser.get().getPassword())) {
////            String token = Jwts.builder()
////                .setSubject(user.getEmail())
////                .claim("role", dbUser.get().getRole())
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
////                .signWith(Keys.hmacShaKeyFor("SecretKeyForJWTSecretKeyForJWT".getBytes()), SignatureAlgorithm.HS256)
////                .compact();
////            return ResponseEntity.ok(Collections.singletonMap("token", token));
////        }
////        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
////    }
////}
//package com.example.controller;
//import java.util.Collections;
//import java.util.Date;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.entity.User;
//import com.example.enums.UserRole;
//import com.example.repository.UserRepository;
//import com.example.service.UserService;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//@RestController
//@RequestMapping("/auth")
//@CrossOrigin(origins ="http://localhost:4200")
//public class AuthController {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/signup")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(UserRole.USER);
//        userRepository.save(user);
//        
//        return ResponseEntity.ok("User registered successfully");
//        
//    }
//
//    
//    
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
//
//        if (dbUser.isPresent() && passwordEncoder.matches(user.getPassword(), dbUser.get().getPassword())) {
//
//            byte[] keyBytes = "SecretKeyForJWTSecretKeyForJWT".getBytes(); // 32+ chars
//            String token = Jwts.builder()
//                    .setSubject(dbUser.get().getEmail())
//                    .claim("role", dbUser.get().getRole())
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
//                    .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS256)
//                    .compact();
//
//            return ResponseEntity.ok(Collections.singletonMap("token", token));
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }
//}