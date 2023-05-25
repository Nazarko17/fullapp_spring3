package com.example.fullapp_spring3.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ExamDTO {

    int id;
    String title;
    String description;
    int maxPoints;
    int numberOfQuestions;
    int passPercentage;
    boolean isActive;
    String difficulty;

    CategoryDTO categoryDTO;

    public ExamDTO(int id, String title, String description, int maxPoints, int numberOfQuestions, int passPercentage, boolean isActive, String difficulty, CategoryDTO categoryDTO) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.maxPoints = maxPoints;
        this.numberOfQuestions = numberOfQuestions;
        this.passPercentage = passPercentage;
        this.isActive = isActive;
        this.difficulty = difficulty;
        this.categoryDTO = categoryDTO;
    }
}
