package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.QuestionDAO;
import com.example.fullapp_spring3.dtos.QuestionDTO;
import com.example.fullapp_spring3.dtos.QuestionDTOMapper;
import com.example.fullapp_spring3.models.Exam;
import com.example.fullapp_spring3.models.ExamResult;
import com.example.fullapp_spring3.models.Question;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final ExamService examService;
    private final QuestionDAO questionDAO;
    private final ModelMapper modelMapper;
    private final QuestionDTOMapper questionDTOMapper;

    public QuestionDTO findQuestion(int id) {
        return questionDTOMapper.apply(questionDAO.findById(id));
    }

    public QuestionDTO saveQuestion(QuestionDTO questionDTO) {
        Question question = questionDAO.save(convertToEntity(questionDTO));
        QuestionDTO questionDTO2 = convertToDto(question);
        questionDTO2.setExamDTO(examService.findExam(questionDTO.getExamDTO().getId()));
        examService.saveMaxPointsAndNumberOfQuestions(questionDTO.getExamDTO().getId());
        return questionDTO2;
    }

    public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
        Question question = questionDAO.save(convertToEntity(questionDTO));
        QuestionDTO questionDTO2 = convertToDto(question);
        questionDTO2.setExamDTO(examService.findExam(questionDTO.getExamDTO().getId()));
        examService.saveMaxPointsAndNumberOfQuestions(questionDTO.getExamDTO().getId());
        return questionDTO2;
    }

    public void deleteQuestion(int id) {
        Exam exam = questionDAO.findById(id).getExam();
        questionDAO.deleteById(id);
        examService.saveMaxPointsAndNumberOfQuestions(exam.getId());
    }

    public Set<QuestionDTO> finQuestionsByExamAsAdmin(int id) {
        return questionDAO.findByExamId(id).stream().map(questionDTOMapper).collect(Collectors.toSet());
    }

    public ExamResult evaluateExam(List<QuestionDTO> questionsDTO) {
        return new ExamResult(calculateAchievedPoints(questionsDTO), calculateCorrectAnswers(questionsDTO), checkIsPassed(questionsDTO));
    }

    public int calculateAchievedPoints(List<QuestionDTO> questionsDTO) {
        return questionsDTO.stream()
                .filter(q -> (q.getAnswer().equals(q.getCorrectAnswer())))
                .mapToInt(QuestionDTO::getPoints)
                .sum();
    }

    public int calculateCorrectAnswers(List<QuestionDTO> questionsDTO) {
        return (int) questionsDTO.stream()
                .filter(q -> (q.getAnswer().equals(q.getCorrectAnswer())))
                .count();
    }

    public boolean checkIsPassed(List<QuestionDTO> questionsDTO) {
        return calculateAchievedPoints(questionsDTO) >= examService.calculatePointsToPass(questionsDTO.get(0).getExamDTO().getId());
    }

    public QuestionDTO convertToDto(Question question) {
        return modelMapper.map(question, QuestionDTO.class);
    }

    public Question convertToEntity(QuestionDTO questionDTO) {
        return modelMapper.map(questionDTO, Question.class);
    }
}