package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.ExamResultDAO;
import com.example.fullapp_spring3.daos.QuestionDAO;
import com.example.fullapp_spring3.dtos.*;
import com.example.fullapp_spring3.models.Exam;
import com.example.fullapp_spring3.models.ExamResult;
import com.example.fullapp_spring3.models.Question;
import com.example.fullapp_spring3.models.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final ExamService examService;
    private final QuestionDAO questionDAO;
    private final ModelMapper modelMapper;
    private final ExamResultDAO examResultDAO;
    private final QuestionDTOMapper questionDTOMapper;
    private final ExamResultDTOMapper examResultDTOMapper;
    private final UserDetailsServiceImpl userDetailsService;

    public QuestionDTO findQuestion(int id) {
        return questionDTOMapper.apply(questionDAO.findById(id));
    }

    // examDTO returns "null"
    public QuestionDTO saveQuestion(QuestionDTO questionDTO) {
        Question question = questionDAO.save(convertToEntity(questionDTO));
//        QuestionDTO questionDTO2 = convertToDto(question);
//        questionDTO2.setExamDTO(examService.findExam(questionDTO.getExamDTO().getId()));
        examService.saveExamParameters(questionDTO.getExamDTO().getId());
        return convertToDto(question);
    }

    public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
        Question question = questionDAO.save(convertToEntity(questionDTO));
        examService.saveExamParameters(questionDTO.getExamDTO().getId());
        return questionDTOMapper.apply(question);
    }

    public void deleteQuestion(int id) {
        Exam exam = questionDAO.findById(id).getExam();
        questionDAO.deleteById(id);
        examService.saveExamParameters(exam.getId());
    }

    public Set<QuestionDTO> finQuestionsByExamAsAdmin(int id) {
        return questionDAO.findByExamId(id).stream().map(questionDTOMapper).collect(Collectors.toSet());
    }

    public ExamResultDTO evaluateExam(List<QuestionDTO> questionsDTO, Principal principal) {
        DateFormat df = new SimpleDateFormat("hh:mm dd-MM-yyyy");
        ExamResult examResult = new ExamResult(
                calculateAchievedPoints(questionsDTO),
                calculateCorrectAnswers(questionsDTO),
                df.format(Calendar.getInstance().getTime()),
                checkIsPassed(questionsDTO),
                examService.convertToEntity(questionsDTO.get(0).getExamDTO()),
                (User) userDetailsService.loadUserByUsername(principal.getName()));
        examResultDAO.save(examResult);
        return examResultDTOMapper.apply(examResult);
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
        return calculateAchievedPoints(questionsDTO) >= questionsDTO.get(0).getExamDTO().getPointsToPass();
    }

    public QuestionDTO convertToDto(Question question) {
        return modelMapper.map(question, QuestionDTO.class);
    }

    public Question convertToEntity(QuestionDTO questionDTO) {
        return modelMapper.map(questionDTO, Question.class);
    }
}