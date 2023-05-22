package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.QuestionDAO;
import com.example.fullapp_spring3.dtos.ExamDTO;
import com.example.fullapp_spring3.dtos.QuestionDTO;
import com.example.fullapp_spring3.dtos.QuestionDTOMapper;
import com.example.fullapp_spring3.models.Exam;
import com.example.fullapp_spring3.models.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionDAO questionDAO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private QuestionDTOMapper questionDTOMapper;

    @InjectMocks
    private QuestionService questionService;

    private Question question;
    private QuestionDTO questionDTO;
    private Exam exam;

    @BeforeEach
    void setUp() {
        exam = Exam.builder().id(1).title("Test")
                .description("Description").maxPoints("100")
                .numberOfQuestions("10").isActive(true).build();
        ExamDTO examDTO = ExamDTO.builder().id(1).title("Test")
                .description("Description").maxPoints("10")
                .numberOfQuestions("1").isActive(true).build();
        question = Question.builder().id(5).content("test").option1("1")
                .option2("2").option3("3").option4("4").correctAnswer("3")
                .answer(null).exam(exam).build();
        questionDTO = QuestionDTO.builder().id(5).content("test").option1("1")
                .option2("2").option3("3").option4("4").correctAnswer("3")
                .answer("3").examDTO(examDTO).build();
    }

    @Test
    void findQuestion() {
        when(questionDAO.findById(1)).thenReturn(question);
        when(questionDTOMapper.apply(question)).thenReturn(questionDTO);
        assertEquals(questionDTO, questionService.findQuestion(1));
    }

    @Test
    void saveQuestion() {
        questionDAO.save(questionService.convertToEntity(questionDTO));
        when(questionService.convertToDto(question)).thenReturn(questionDTO);
        assertEquals(questionDTO, questionService.convertToDto(question));
    }

    @Test
    void finQuestionsByExamAsAdmin() {
        Set<Question> questions = new HashSet<>();
        questions.add(question);
        Set<QuestionDTO> questionDTOS = new HashSet<>();
        questionDTOS.add(questionDTO);

        when(questionDAO.findByExam(exam)).thenReturn(questions);
        when(questionDTOMapper.apply(question)).thenReturn(questionDTO);
        assertEquals(questionDTOS, questionDAO.findByExam(exam).stream().map(questionDTOMapper).collect(Collectors.toSet()));
    }

    @Test
    void evaluateExam() {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        questionDTOS.add(questionDTO);
        Map<String, Object> answers = new HashMap<>();
        answers.put("achievedPoints", 10);
        answers.put("correctAnswers", 1);
        assertEquals(answers, questionService.evaluateExam(questionDTOS));
    }

    @Test
    void convertToDto() {
        when(modelMapper.map(question, QuestionDTO.class)).thenReturn(questionDTO);
        assertEquals(questionDTO, questionService.convertToDto(question));
    }

    @Test
    void convertToEntity() {
        when(modelMapper.map(questionDTO, Question.class)).thenReturn(question);
        assertEquals(question, questionService.convertToEntity(questionDTO));
    }
}