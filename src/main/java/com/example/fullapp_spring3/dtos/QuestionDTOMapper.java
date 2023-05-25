package com.example.fullapp_spring3.dtos;

import com.example.fullapp_spring3.models.Question;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class QuestionDTOMapper implements Function<Question, QuestionDTO> {

    ExamDTOMapper examDTOMapper = new ExamDTOMapper();

    @Override
    public QuestionDTO apply(Question question) {
        return new QuestionDTO(
                question.getId(),
                question.getContent(),
                question.getPoints(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4(),
                question.getCorrectAnswer(),
                question.getAnswer(),
                examDTOMapper.apply(question.getExam())
        );
    }
}
