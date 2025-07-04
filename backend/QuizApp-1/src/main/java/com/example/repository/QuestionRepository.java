package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; 
import com.example.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Modifying
    @Transactional 
    @Query("DELETE FROM Question q WHERE q.quiz.id = :quizId")
    void deleteByQuizId(@Param("quizId") Long quizId);
}
