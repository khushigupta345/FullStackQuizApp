package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.QuestionAnswer;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
}