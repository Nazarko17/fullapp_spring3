package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.QuestionDAO;
import com.example.fullapp_spring3.dtos.QuestionDTO;
import com.example.fullapp_spring3.dtos.QuestionDTOMapper;
import com.example.fullapp_spring3.models.Exam;
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
        return questionDTOMapper.apply(questionDAO.findById(id).get());
    }

    public QuestionDTO saveQuestion(QuestionDTO questionDTO) {
        Question question = questionDAO.save(convertToEntity(questionDTO));
        QuestionDTO questionDTO2 = convertToDto(question);
        questionDTO2.setExamDTO(examService.findExam(questionDTO.getExamDTO().getId()));
        return questionDTO2;
    }

    public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
        Question question = questionDAO.save(convertToEntity(questionDTO));
        QuestionDTO questionDTO2 = convertToDto(question);
        questionDTO2.setExamDTO(examService.findExam(questionDTO.getExamDTO().getId()));
        return questionDTO2;
    }

    public void deleteQuestion(int id) {
        Question question = new Question();
        question.setId(id);
        questionDAO.delete(question);
    }

    public Set<QuestionDTO> finQuestionsByExamAsAdmin(int id) {
        Exam exam = new Exam();
        exam.setId(id);
        return questionDAO.findByExam(exam).stream().map(questionDTOMapper).collect(Collectors.toSet());
    }

    public Map<String, Object> evaluateExam(List<QuestionDTO> questionsDTO) {
        int[] achievedPoints = {0};
        int[] correctAnswers = {0};

        questionsDTO.stream()
                .forEach(q -> {
                    if (q.getAnswer().equals(q.getCorrectAnswer())) {
                        correctAnswers[0]++;
                        achievedPoints[0] += Integer.parseInt(questionsDTO.get(0).getExamDTO().getMaxPoints()) / questionsDTO.size();
                    }
                });

        Map<String, Object> answers = new HashMap<>();
        answers.put("achievedPoints", achievedPoints[0]);
        answers.put("correctAnswers", correctAnswers[0]);
        return answers;
    }

    public QuestionDTO convertToDto(Question question) {
        return modelMapper.map(question, QuestionDTO.class);
    }

    public Question convertToEntity(QuestionDTO questionDTO) {
        return modelMapper.map(questionDTO, Question.class);
    }
}

