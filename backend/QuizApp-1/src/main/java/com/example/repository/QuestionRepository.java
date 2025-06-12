package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Question;
import com.example.entity.Quiz;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {



}



