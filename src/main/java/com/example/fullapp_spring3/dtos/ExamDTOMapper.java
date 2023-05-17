package com.example.fullapp_spring3.dtos;

import com.example.fullapp_spring3.models.Exam;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExamDTOMapper implements Function<Exam, ExamDTO> {

    CategoryDTOMapper categoryDTOMapper = new CategoryDTOMapper();

    @Override
    public ExamDTO apply(Exam exam) {
        return new ExamDTO(
                exam.getId(),
                exam.getTitle(),
                exam.getDescription(),
                exam.getMaxPoints(),
                exam.getNumberOfQuestions(),
                exam.isActive(),
                categoryDTOMapper.apply(exam.getCategory())
        );
    }
}
