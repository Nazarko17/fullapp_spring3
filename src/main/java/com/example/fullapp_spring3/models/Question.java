package com.example.fullapp_spring3.models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 800)
    private String content;
    private String image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    @Transient
    private String correctAnswer;
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Exam exam;
}
