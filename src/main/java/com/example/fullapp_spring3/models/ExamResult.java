package com.example.fullapp_spring3.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exam_results")
public class ExamResult {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int achievedPoints;
    private int correctAnswers;
    private String completionTime;

    @JsonProperty("isPassed")
    private boolean isPassed;

    @ManyToOne(fetch = FetchType.EAGER)
    private Exam exam;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public ExamResult(int achievedPoints, int correctAnswers, String completionTime, boolean isPassed, Exam exam, User user) {
        this.achievedPoints = achievedPoints;
        this.correctAnswers = correctAnswers;
        this.completionTime = completionTime;
        this.isPassed = isPassed;
        this.exam = exam;
        this.user = user;
    }
}

