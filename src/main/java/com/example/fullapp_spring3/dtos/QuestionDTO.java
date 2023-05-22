package com.example.fullapp_spring3.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class QuestionDTO {
    int id;
    String content;
    String image;
    String option1;
    String option2;
    String option3;
    String option4;
    String correctAnswer;
    String answer;

    ExamDTO examDTO;

    public QuestionDTO(int id, String content, String image, String option1, String option2, String option3, String option4, String correctAnswer, String answer, ExamDTO examDTO) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
        this.answer = answer;
        this.examDTO = examDTO;
    }
}
