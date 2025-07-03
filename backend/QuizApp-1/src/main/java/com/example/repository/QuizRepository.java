package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DTO.QuizDTO;
import com.example.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {


}



