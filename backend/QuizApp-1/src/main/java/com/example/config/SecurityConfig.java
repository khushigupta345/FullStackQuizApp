package com.example.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.entity.User;
import com.example.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtFilter jwtFilter;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService,
                          JwtFilter jwtFilter,
                          JwtService jwtService,
                          UserRepository userRepository) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.jwtFilter = jwtFilter;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors()
            .and()
            .authorizeHttpRequests()
                .requestMatchers(
                    "/signup", "/api", "/auth/signup", "/auth/login",
                    "/oauth2/", "/login/oauth2/", "/login"
                ).permitAll()
//                .requestMatchers("/api/test-result/").hasAuthority("ADMIN")
                .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/api/admin").hasAuthority("ADMIN")
                .requestMatchers("/api/admin/test-result").hasAuthority("ADMIN")
//                .requestMatchers("/api/user/").hasAuthority("USER")
                .requestMatchers("/api/user/**").hasAuthority("USER")  
                .anyRequest().authenticated()
                
            .and()
            .oauth2Login(oauth -> oauth
                .authorizationEndpoint(config -> config
                    .authorizationRequestRepository(authorizationRequestRepository()))
                .userInfoEndpoint(user -> user
                    .userService(customOAuth2UserService))
                .successHandler((request, response, authentication) -> {
                    OAuth2User oAuthUser = (OAuth2User) authentication.getPrincipal();
                    String email = oAuthUser.getAttribute("email");

                    Optional<User> existingUser = userRepository.findByEmail(email);
                    if (existingUser.isEmpty()) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"User not registered\"}");
                        return;
                    }

                    String role = existingUser.get().getRole().name();
                    Long id = existingUser.get().getId();

                    String jwt = jwtService.generateToken(email, role, id);
                    System.out.println(id);
                    String redirectUrl = "http://localhost:4200/login-success?token=" + jwt;
                    response.sendRedirect(redirectUrl);
                })
            );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // <- fix for all routes
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true);
            }
        };
    }
}
//package com.example.config;
//
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
//import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.example.entity.User;
//import com.example.repository.UserRepository;
//
//import jakarta.servlet.http.HttpServletResponse;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final JwtFilter jwtFilter;
//    private final JwtService jwtService;
//    private final UserRepository userRepository;
//
//    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService,
//                          JwtFilter jwtFilter,
//                          JwtService jwtService,
//                          UserRepository userRepository) {
//        this.customOAuth2UserService = customOAuth2UserService;
//        this.jwtFilter = jwtFilter;
//        this.jwtService = jwtService;
//        this.userRepository = userRepository;
//    }
//
//    @Bean
//    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
//        return new HttpSessionOAuth2AuthorizationRequestRepository();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeHttpRequests()
//            .requestMatchers(
//                "/signup", "/api", "/auth/signup", "/auth/login",
//                "/oauth2/", "/login/oauth2/", "/login"
//            ).permitAll()
//            .requestMatchers("/api/test-result").hasAuthority("ADMIN")
//            .requestMatchers("/api/test-result/**").hasAuthority("USER")
//            .anyRequest().authenticated()
//            .and()
//            .oauth2Login(oauth -> oauth
//                .authorizationEndpoint(config -> config
//                    .authorizationRequestRepository(authorizationRequestRepository()))
//                .userInfoEndpoint(user -> user
//                    .userService(customOAuth2UserService))
//                .successHandler((request, response, authentication) -> {
//                    OAuth2User oAuthUser = (OAuth2User) authentication.getPrincipal();
//                    String email = oAuthUser.getAttribute("email");
//
//                    Optional<User> existingUser = userRepository.findByEmail(email);
//                    if (existingUser.isEmpty()) {
//                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        response.setContentType("application/json");
//                        response.getWriter().write("{\"error\": \"User not registered\"}");
//                        return;
//                    }
//
//                    String role = existingUser.get().getRole().name();
//                    Long id = existingUser.get().getId();
//
//                    String jwt = jwtService.generateToken(email, role, id);
//                    String redirectUrl = "http://localhost:4200/login-success?token=" + jwt;
//                    response.sendRedirect(redirectUrl);
//                })
//            );
//
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/")
//                        .allowedOrigins("http://localhost:4200")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowCredentials(true);
//            }
//        };
//    }
//}
////package com.example.config;
////
////import java.util.Map;
////import java.util.Optional;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
////import org.springframework.security.oauth2.core.user.OAuth2User;
////import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
////import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import org.springframework.web.servlet.config.annotation.CorsRegistry;
////import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
////
////import com.example.entity.User;
////import com.example.repository.UserRepository;
////
////
////import jakarta.servlet.http.HttpServletResponse;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    private final CustomOAuth2UserService customOAuth2UserService;
////    private final JwtFilter jwtFilter;
////    private final JwtService jwtService;
////    private final UserRepository userRepository;
////
////    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService,
////                          JwtFilter jwtFilter,
////                          JwtService jwtService,
////                          UserRepository userRepository) {
////        this.customOAuth2UserService = customOAuth2UserService;
////        this.jwtFilter = jwtFilter;
////        this.jwtService = jwtService;
////        this.userRepository = userRepository;
////    }
////
////    @Bean
////    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
////        return new HttpSessionOAuth2AuthorizationRequestRepository();
////    }
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////            .csrf().disable()
////            .authorizeHttpRequests()
////            .requestMatchers(
////                "/signup", "/api", "/auth/signup", "/auth/login",
////                "/oauth2/", "/login/oauth2/", "/login"
////            ).permitAll()
////            .requestMatchers("/api/test-result").hasAuthority("ADMIN")
////            .requestMatchers("/api/test-result/").hasAuthority("USER")
////            .anyRequest().authenticated()
////            .and()
////            .oauth2Login(oauth -> oauth
////                .authorizationEndpoint(config -> config
////                    .authorizationRequestRepository(authorizationRequestRepository()))
////                .userInfoEndpoint(user -> user
////                    .userService(customOAuth2UserService))
////                .successHandler((request, response, authentication) -> {
////                    OAuth2User oAuthUser = (OAuth2User) authentication.getPrincipal();
////                    String email = oAuthUser.getAttribute("email");
////
////                    Optional<User> existingUser = userRepository.findByEmail(email);
////                    if (existingUser.isEmpty()) {
////                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////                        response.setContentType("application/json");
////                        response.getWriter().write("{\"error\": \"User not registered\"}");
////                        return;
////                    }
////
////                    String role = existingUser.get().getRole().name();
////                    Long id = existingUser.get().getId();
////
////                    String jwt = jwtService.generateToken(Map.of("role", role), email, id);
////
////                    String redirectUrl = "http://localhost:4200/login-success?token=" + jwt;
////                    response.sendRedirect(redirectUrl);
////                })
////            );
////
////        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
////
////    @Bean
////    public WebMvcConfigurer corsConfigurer() {
////        return new WebMvcConfigurer() {
////            @Override
////            public void addCorsMappings(CorsRegistry registry) {
////                registry.addMapping("/")
////                        .allowedOrigins("http://localhost:4200")
////                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
////                        .allowCredentials(true);
////            }
////        };
////    }
////}
////package com.example.config;
////
////import java.util.Map;
////import java.util.Optional;
////
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import org.springframework.web.servlet.config.annotation.CorsRegistry;
////import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
////import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
////import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
////import org.springframework.security.oauth2.core.user.OAuth2User;
////import org.springframework.security.web.SecurityFilterChain;
////
////import com.example.entity.User;
////import com.example.repository.UserRepository;
////
////import jakarta.servlet.http.HttpServletResponse;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Autowired
////    private CustomOAuth2UserService customOAuth2UserService;
////
////    @Autowired
////    private JwtFilter jwtFilter;
////
////    @Autowired
////    private JwtService jwtService;
////
////    @Autowired
////    private UserRepository userRepository;
////
////    // ✅ Add this bean to fix [authorization_request_not_found]
////    @Bean
////    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
////        return new HttpSessionOAuth2AuthorizationRequestRepository();
////    }
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////            .csrf().disable()
////            .authorizeHttpRequests()
////                .requestMatchers(
////                    "/signup", "/api", "/auth/signup", "/auth/login",
////                    "/oauth2/", "/login/oauth2/", "/login"
////                ).permitAll()
////                .requestMatchers("/api/test-result").hasAuthority("ADMIN")
////                .requestMatchers("/api/test-result/").hasAuthority("USER")
////                .anyRequest().authenticated()
////            .and()
////            .oauth2Login()
////                .authorizationEndpoint()
////                    .authorizationRequestRepository(authorizationRequestRepository()) // ✅ important line
////                .and()
////                .userInfoEndpoint()
////                    .userService(customOAuth2UserService)
////                .and()
////                .successHandler((request, response, authentication) -> {
////                    OAuth2User oAuthUser = (OAuth2User) authentication.getPrincipal();
////                    String email = oAuthUser.getAttribute("email");
////
////                    Optional<User> existingUser = userRepository.findByEmail(email);
////                    if (existingUser.isEmpty()) {
////                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////                        response.setContentType("application/json");
////                        response.getWriter().write("{\"error\": \"User not registered\"}");
////                        return;
////                    }
////
////                    String role = existingUser.get().getRole().name();
////                    Long id = existingUser.get().getId();
////                    String jwt = jwtService.generateToken(Map.of("role", role), email, id);
////
////                    String redirectUrl = "http://localhost:4200/login-success?token=" + jwt;
////                    response.sendRedirect(redirectUrl);
////                });
////
////        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
////
////    @Bean
////    public WebMvcConfigurer corsConfigurer() {
////        return new WebMvcConfigurer() {
////            @Override
////            public void addCorsMappings(CorsRegistry registry) {
////                registry.addMapping("/")
////                        .allowedOrigins("http://localhost:4200")
////                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
////                        .allowCredentials(true);
////            }
////        };
////    }
////}
////package com.example.config;
////import java.util.Map;
////import java.util.Optional;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import org.springframework.web.servlet.config.annotation.CorsRegistry;
////import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.oauth2.core.user.OAuth2User;
////import org.springframework.security.web.SecurityFilterChain;
////
////import com.example.entity.User;
////import com.example.repository.UserRepository;
////
////import jakarta.servlet.http.HttpServletResponse;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Autowired
////    private CustomOAuth2UserService customOAuth2UserService;
////
////    @Autowired
////    private JwtFilter jwtFilter;
////
////    @Autowired
////    private JwtService jwtService;
////
////    @Autowired
////    private UserRepository userRepository; // ✅ यह जरूरी है
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////            .csrf().disable()
////            .authorizeHttpRequests()
////            .requestMatchers(
////            		"/signup","/api",
////				  "/auth/signup", "/auth/login",
//// 				  "/oauth2/**", "/login/oauth2/**","/login"
//// 				).permitAll()
////            .requestMatchers("/api/test-result").hasAuthority("ADMIN") 
////        .requestMatchers("/api/test-result/").hasAuthority("USER")
////        .anyRequest().authenticated()
////        
////              
////            .and()
////            .oauth2Login()
////                .userInfoEndpoint()
////                    .userService(customOAuth2UserService)
////            .and()
////            .successHandler((request, response, authentication) -> {
////                OAuth2User oAuthUser = (OAuth2User) authentication.getPrincipal();
////                String email = oAuthUser.getAttribute("email");
////
////                // ✅ Check if user exists in DB
////                Optional<User> existingUser = userRepository.findByEmail(email);
////                if (existingUser.isEmpty()) {
////                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////                    response.setContentType("application/json");
////                    response.getWriter().write("{\"error\": \"User not registered\"}");
////                    return;
////                }
////
////                String role = existingUser.get().getRole().name(); // ✅ Enum to String (like "ROLE_ADMIN")
////                Long  id = existingUser.get().getId();
////                String jwt = jwtService.generateToken(Map.of("role", role), email,id); // ✅ claims added
////                
////
//////                response.setContentType("application/json");
//////                response.getWriter().write("{\"token\": \"" + jwt + "\"}");
////                String redirectUrl = "http://localhost:4200/login-success?token=" + jwt;
////                response.sendRedirect(redirectUrl);
////                
////                
////            });
////
//////        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
////        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    } 
////  @Bean
////  public WebMvcConfigurer corsConfigurer() {
////      return new WebMvcConfigurer() {
////          @Override
////          public void addCorsMappings(CorsRegistry registry) {
////              registry.addMapping("/**") // ✅ CORS Mapping Fix
////                      .allowedOrigins("http://localhost:4200") 
////                      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // ✅ OPTIONS Add
////                      .allowCredentials(true);
////          }
////      };
////  }
////}
////package com.example.config;
////import java.util.Map;
////import java.util.Optional;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import org.springframework.web.servlet.config.annotation.CorsRegistry;
////import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.oauth2.core.user.OAuth2User;
////import org.springframework.security.web.SecurityFilterChain;
////
////import com.example.entity.User;
////import com.example.repository.UserRepository;
////
////import jakarta.servlet.http.HttpServletResponse;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Autowired
////    private CustomOAuth2UserService customOAuth2UserService;
////
////    @Autowired
////    private JwtFilter jwtFilter;
////
////    @Autowired
////    private JwtService jwtService;
////
////    @Autowired
////    private UserRepository userRepository; // ✅ यह जरूरी है
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////            .csrf().disable()
////            .authorizeHttpRequests()
////            .requestMatchers(
////            		"/signup","/api",
////				  "/auth/signup", "/auth/login",
//// 				  "/oauth2/**", "/login/oauth2/**","/login"
//// 				).permitAll()
////            .requestMatchers("/api/test-result").hasAuthority("ADMIN") 
////        .requestMatchers("/api/test-result/").hasAuthority("USER")
////        .anyRequest().authenticated()
////        
////              
////            .and()
////            .oauth2Login()
////                .userInfoEndpoint()
////                    .userService(customOAuth2UserService)
////            .and()
////            .successHandler((request, response, authentication) -> {
////                OAuth2User oAuthUser = (OAuth2User) authentication.getPrincipal();
////                String email = oAuthUser.getAttribute("email");
////
////                // ✅ Check if user exists in DB
////                Optional<User> existingUser = userRepository.findByEmail(email);
////                if (existingUser.isEmpty()) {
////                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////                    response.setContentType("application/json");
////                    response.getWriter().write("{\"error\": \"User not registered\"}");
////                    return;
////                }
////
////                String role = existingUser.get().getRole().name(); // ✅ Enum to String (like "ROLE_ADMIN")
////                String jwt = jwtService.generateToken(Map.of("role", role), email); // ✅ claims added
////
////                response.setContentType("application/json");
////                response.getWriter().write("{\"token\": \"" + jwt + "\"}");
////            });
////
//////        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
////        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    } 
////  @Bean
////  public WebMvcConfigurer corsConfigurer() {
////      return new WebMvcConfigurer() {
////          @Override
////          public void addCorsMappings(CorsRegistry registry) {
////              registry.addMapping("/**") // ✅ CORS Mapping Fix
////                      .allowedOrigins("http://localhost:4200") 
////                      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // ✅ OPTIONS Add
////                      .allowCredentials(true);
////          }
////      };
////  }
////}
//
//
////
////package com.example.config;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.web.servlet.config.annotation.CorsRegistry;
////import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
////import org.springframework.security.config.http.SessionCreationPolicy;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////            .csrf().disable()
////            .cors().and()  // ✅ CORS Error Fix
////           .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ Stateless Session
////            .authorizeHttpRequests(auth -> auth
////            		.requestMatchers(
////            				  "/auth/signup", "/auth/login",
////            				  "/oauth2/**", "/login/oauth2/**","/login"
////            				).permitAll()
//////                .requestMatchers("/auth/signup", "/auth/login").permitAll() // ✅ Allow Signup/Login
////            		  .requestMatchers("/api/test-result/**").hasAuthority("USER") // ✅ Role Check
////                .requestMatchers("/api/test-result").hasAuthority("ADMIN")  // ✅ Role Check
////                .requestMatchers("/api/test").hasAuthority("ADMIN")  // ✅ Role Check
////                
////              
////                .anyRequest().authenticated()
////            )
////           .oauth2Login(oauth -> oauth
////              .loginPage("/auth/login")
////              .permitAll()
////          );
////
////        return http.build();
////    }
////
////    @Bean
////    public WebMvcConfigurer corsConfigurer() {
////        return new WebMvcConfigurer() {
////            @Override
////            public void addCorsMappings(CorsRegistry registry) {
////                registry.addMapping("/**") // ✅ CORS Mapping Fix
////                        .allowedOrigins("http://localhost:4200") 
////                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // ✅ OPTIONS Add
////                        .allowCredentials(true);
////            }
////        };
////    }
////}
////package com.example.config;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////            .csrf().disable()
////            .authorizeHttpRequests(auth -> auth
//////                .requestMatchers("/auth/signup", "/auth/login", "/auth/google").permitAll()
////                .requestMatchers("/api/test-result").hasAuthority("ADMIN") 
////                .requestMatchers("/api/test-result/").hasAuthority("USER")
////                .anyRequest().authenticated()
////            )
////            .oauth2Login(oauth -> oauth
////                .loginPage("/auth/login")
////                .permitAll()
////            );
////
////        return http.build();
////    }
////}
////package com.example.config;
////
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
////
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig extends WebSecurityConfiguration {
////
////    // Configuring HTTP Security and OAuth2 login
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////            .authorizeRequests()
////            .antMatchers("/api/test-result").hasRole("ADMIN") // Only admin can access all results
////            .antMatchers("/api/test-result/").hasRole("USER") // User can access their own results, using ** for dynamic segments
////            .anyRequest().authenticated() // Authenticate all other requests
////            .and()
////            .oauth2Login() // Google OAuth2 login
////            .loginPage("/login") // Optional: Customize login page if needed
////            .permitAll(); // Allow unauthenticated access to login page
////    }
////}
////package com.example.config;
////
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
////
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig extends WebSecurityConfiguration {
////
////    // Configuring HTTP Security and OAuth2 login
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////            .authorizeRequests()
////            .antMatchers("/api/test-result").hasRole("ADMIN") // Only admin can access all results
////            .antMatchers("/api/test-result/").hasRole("USER") // User can access their own results, using ** for dynamic segments
////            .anyRequest().authenticated() // Authenticate all other requests
////            .and()
////            .oauth2Login() // Google OAuth2 login
////            .loginPage("/login") // Optional: Customize login page if needed
////            .permitAll(); // Allow unauthenticated access to login page
////    }
////}