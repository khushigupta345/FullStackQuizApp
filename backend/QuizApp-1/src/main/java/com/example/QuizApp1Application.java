package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication

@EnableTransactionManagement
public class QuizApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(QuizApp1Application.class, args);
	}

}
