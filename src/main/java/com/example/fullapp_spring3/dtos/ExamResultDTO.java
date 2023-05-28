package com.example.fullapp_spring3.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExamResultDTO {
    private int id;
    private String examTitle;
    private int achievedPoints;
    private int correctAnswers;
    private String completionTime;

    @JsonProperty("isPassed")
    private boolean isPassed;

    private UserDTO userDTO;
}
