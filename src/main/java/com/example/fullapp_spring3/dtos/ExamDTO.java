package com.example.fullapp_spring3.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@NoArgsConstructor
@ToString
public class ExamDTO {

    int id;
    String title;
    String description;
    int maxPoints;
    int numberOfQuestions;
    int passPercentage;
    int pointsToPass;
    String difficulty;
    boolean isActive;

    CategoryDTO categoryDTO;

    public ExamDTO(int id, String title, String description, int maxPoints, int numberOfQuestions, int passPercentage, int pointsToPass, String difficulty, boolean isActive, CategoryDTO categoryDTO) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.maxPoints = maxPoints;
        this.numberOfQuestions = numberOfQuestions;
        this.passPercentage = passPercentage;
        this.pointsToPass = pointsToPass;
        this.difficulty = difficulty;
        this.isActive = isActive;
        this.categoryDTO = categoryDTO;
    }
}
