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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionDAO questionDAO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ExamService examService;
    @Mock
    private QuestionDTOMapper questionDTOMapper;

    @InjectMocks
    private QuestionService questionService;

    private Question question;
    private QuestionDTO questionDTO;
    private List<QuestionDTO> questionDTOs;

    @BeforeEach
    void setUp() {
        Exam exam = Exam.builder().id(1).title("Test").passPercentage(80)
                .description("Description").isActive(true).build();
        ExamDTO examDTO = ExamDTO.builder().id(1).title("Test")
                .description("Description").isActive(true).build();
        question = Question.builder().id(5).content("test").option1("1")
                .option2("2").option3("3").option4("4").correctAnswer("3")
                .answer("3").exam(exam).points(10).build();
        questionDTO = QuestionDTO.builder().id(5).content("test").option1("1")
                .option2("2").option3("3").option4("4").correctAnswer("3")
                .answer("3").examDTO(examDTO).points(10).build();
        questionDTOs = List.of(questionDTO,
                QuestionDTO.builder().points(10).correctAnswer("pam-param")
                        .answer("NEpam-param").build(),
                QuestionDTO.builder().points(10).correctAnswer("3")
                        .answer("3").build());
    }

    @Test
    void findQuestion() {
        when(questionService.findQuestion(5)).thenReturn(questionDTO);
        QuestionDTO questionDTO1 = questionService.findQuestion(5);
        assertThat(questionDTO1).isNotNull();
        assertThat(questionDTO1.getId()).isEqualTo(5);
    }

    // TODO:
    @Test
    void saveQuestion() {
        when(questionService.saveQuestion(questionDTO)).thenReturn(questionDTO);
        QuestionDTO questionDTO1 = questionService.saveQuestion(questionDTO);
        assertThat(questionDTO1).isNotNull();
        assertThat(questionDTO1.getId()).isGreaterThan(0);
        assertThat(questionDTO1.getPoints()).isEqualTo(10);
    }

    @Test
    void finQuestionsByExamAsAdmin() {
        List<Question> questions = List.of(question);
        List<QuestionDTO> questionDTOs = List.of(questionDTO);

        when(questionDAO.findAll()).thenReturn(questions);
        when(questionDTOMapper.apply(question)).thenReturn(questionDTO);
        assertThat(questionDTOs).isNotNull();
        assertThat(questionDTOs).size().isEqualTo(1);
        assertEquals(questionDTOs, questionDAO.findAll().stream().map(questionDTOMapper).collect(Collectors.toList()));
    }

    @Test
    void calculateAchievedPoints() {
        assertThat(questionService.calculateAchievedPoints(questionDTOs)).isEqualTo(20);
    }

    @Test
    void calculateCorrectAnswers() {
        assertThat(questionService.calculateCorrectAnswers(questionDTOs)).isEqualTo(2);
    }

    @Test
    void isPassed() {
        when(examService.calculatePointsToPass(1)).thenReturn(24);
        assertThat(questionService.checkIsPassed(questionDTOs)).isFalse();
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