package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Question;
import com.example.entity.Quiz;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

@Modifying
@Query("DELETE FROM Question q WHERE q.quiz.id = :quizId")
void deleteByQuizId(@Param("quizId") Long quizId);

}



