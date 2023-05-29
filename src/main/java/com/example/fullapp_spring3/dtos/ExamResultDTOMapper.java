package com.example.fullapp_spring3.dtos;

import com.example.fullapp_spring3.models.ExamResult;
import com.example.fullapp_spring3.models.Question;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExamResultDTOMapper  implements Function<ExamResult, ExamResultDTO> {

    UserDTOMapper userDTOMapper = new UserDTOMapper();
    ExamDTOMapper examDTOMapper = new ExamDTOMapper();

    @Override
    public ExamResultDTO apply(ExamResult examResult) {
        return new ExamResultDTO(
                examResult.getId(),
                examResult.getAchievedPoints(),
                examResult.getCorrectAnswers(),
                examResult.getCompletionTime(),
                examResult.isPassed(),
                examDTOMapper.apply(examResult.getExam()),
                userDTOMapper.apply(examResult.getUser())
        );
    }
}
