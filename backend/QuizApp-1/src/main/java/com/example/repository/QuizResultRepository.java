package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DTO.QuizResultDTO;

import com.example.entity.QuizResult;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> { 
List<QuizResult>findAllByUserId(Long id);



}



