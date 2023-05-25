package com.example.fullapp_spring3.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
public class ExamResult {

    private int achievedPoints;
    private int correctAnswers;

    @JsonProperty("isPassed")
    private boolean isPassed;
}
