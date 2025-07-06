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
                    String redirectUrl = "https://full-stack-quiz-app-khushis-projects-d7056416.vercel.app//login-success?token=" + jwt;
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
                        .allowedOrigins("https://full-stack-quiz-app-rho.vercel.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true);
            }
        };
    }
}

