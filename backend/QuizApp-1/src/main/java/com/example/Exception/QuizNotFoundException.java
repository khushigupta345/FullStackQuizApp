package com.example.Exception;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException(String message) {
        super(message);
    }
}
