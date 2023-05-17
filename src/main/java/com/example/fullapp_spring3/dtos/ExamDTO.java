package com.example.fullapp_spring3.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamDTO {

    int id;
    String title;
    String description;
    String maxPoints;
    String numberOfQuestions;
    boolean isActive;

    CategoryDTO categoryDTO;

    public ExamDTO(int id, String title, String description, String maxPoints, String numberOfQuestions, boolean isActive, CategoryDTO categoryDTO) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.maxPoints = maxPoints;
        this.numberOfQuestions = numberOfQuestions;
        this.isActive = isActive;
        this.categoryDTO = categoryDTO;
    }
}
