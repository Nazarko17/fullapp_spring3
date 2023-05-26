package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.ExamDAO;
import com.example.fullapp_spring3.dtos.CategoryDTO;
import com.example.fullapp_spring3.dtos.ExamDTO;
import com.example.fullapp_spring3.dtos.ExamDTOMapper;
import com.example.fullapp_spring3.models.Category;
import com.example.fullapp_spring3.models.Exam;
import com.example.fullapp_spring3.models.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamServiceTest {

    @Mock
    private ExamDAO examDAO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ExamDTOMapper examDTOMapper;

    @InjectMocks
    private ExamService examService;

    private Exam exam;
    private ExamDTO examDTO;

    @BeforeEach
    void setUp() {
        Set<Question> questions = Set.of(
                Question.builder().points(10).build(),
                Question.builder().points(15).build(),
                Question.builder().points(20).build());
        Category category = Category.builder().id(3).build();
        CategoryDTO categoryDTO = CategoryDTO.builder().id(3).build();
        exam = Exam.builder().id(1).title("Test")
                .description("Description").isActive(true)
                .category(category).questions(questions)
                .passPercentage(80).category(category).build();
        examDTO = ExamDTO.builder().id(1).title("Test")
                .description("Description").isActive(true)
                .categoryDTO(categoryDTO)
                .passPercentage(80).build();
    }

    @Test
    void findExam() {
        when(examService.findExam(1)).thenReturn(examDTO);
        ExamDTO examDTO1 = examService.findExam(1);
        assertThat(examDTO1).isNotNull();
        assertThat(examDTO1.getId()).isEqualTo(1);
    }

    @Test
    void findExams() {
        List<Exam> exams = List.of(exam);
        List<ExamDTO> examDTOs = List.of(examDTO);

        when(examDAO.findAll()).thenReturn(exams);
        when(examDTOMapper.apply(exam)).thenReturn(examDTO);
        assertThat(examDTOs).isNotNull();
        assertThat(examDTOs).size().isEqualTo(1);
        assertEquals(examDTOs, examDAO.findAll().stream().map(examDTOMapper).collect(Collectors.toList()));
    }

    // TODO:
    @Test
    void saveExam() {
        when(examService.saveExam(examDTO)).thenReturn(examDTO);
        ExamDTO examDTO1 = examService.saveExam(examDTO);
        assertThat(examDTO1).isNotNull();
        assertThat(examDTO1.getId()).isGreaterThan(0);
        assertThat(examDTO1.getMaxPoints()).isEqualTo(0);
        assertThat(examDTO1.getNumberOfQuestions()).isEqualTo(0);
        assertThat(examDTO1.getCategoryDTO().getId()).isEqualTo(3);
    }

    @Test
    void findByCategoryIdAndIsActive() {
        List<Exam> exams =  List.of(exam);
        List<ExamDTO> examDTOs =  List.of(examDTO);

        when(examDAO.findByCategoryIdAndIsActive(3, true)).thenReturn(exams);
        when(examDTOMapper.apply(exam)).thenReturn(examDTO);
        assertThat(examDTOs).isNotNull();
        assertThat(examDTOs).size().isEqualTo(1);
        assertEquals(examDTOs, examDAO.findByCategoryIdAndIsActive(3, true).stream().map(examDTOMapper).collect(Collectors.toList()));
    }

    @Test
    void convertToDto() {
        when(modelMapper.map(exam, ExamDTO.class)).thenReturn(examDTO);
        assertEquals(examDTO, examService.convertToDto(exam));
    }

    @Test
    void convertToEntity() {
        when(modelMapper.map(examDTO, Exam.class)).thenReturn(exam);
        assertEquals(exam, examService.convertToEntity(examDTO));
    }

    @Test
    void findMaxPoints() {
        when(examDAO.findById(1)).thenReturn(exam);
        assertThat(examService.findMaxPoints(1)).isEqualTo(45);
    }

    @Test
    void findNumberOfQuestions() {
        assertThat(exam.getQuestions().size()).isEqualTo(3);
    }

    @Test
    void calculatePointsToPass() {
        when(examDAO.findById(1)).thenReturn(exam);
        assertThat(examService.calculatePointsToPass(1)).isEqualTo(36);
    }
}