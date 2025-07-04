package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.DTO.QuizDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Long time;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<QuizResult> quizresult = new ArrayList<>();

    public QuizDTO getDto() {
        QuizDTO dto = new QuizDTO();
        dto.setId(id);
        dto.setDescription(description);
        dto.setTime(time);
        dto.setTitle(title);
        return dto;
    }
}
