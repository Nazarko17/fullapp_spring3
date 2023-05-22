package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.ExamDAO;
import com.example.fullapp_spring3.dtos.CategoryDTO;
import com.example.fullapp_spring3.dtos.ExamDTO;
import com.example.fullapp_spring3.dtos.ExamDTOMapper;
import com.example.fullapp_spring3.models.Category;
import com.example.fullapp_spring3.models.Exam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

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
        Category category = Category.builder().id(3).build();
        CategoryDTO categoryDTO = new CategoryDTO(3, "Test",
                "Description");
        exam = Exam.builder().id(1).title("Test")
                .description("Description").maxPoints("100")
                .numberOfQuestions("10").isActive(true)
                .category(category).build();
        examDTO = ExamDTO.builder().id(1).title("Test")
                .description("Description").maxPoints("100")
                .numberOfQuestions("10").isActive(true)
                .categoryDTO(categoryDTO).build();
    }

    @Test
    void findExam() {
        when(examDAO.findById(1)).thenReturn(exam);
        when(examDTOMapper.apply(exam)).thenReturn(examDTO);
        assertEquals(examDTO, examService.findExam(1));
    }

    @Test
    void findExams() {
        List<Exam> exams = new ArrayList<>();
        exams.add(exam);
        LinkedHashSet<ExamDTO> examDTOS = new LinkedHashSet<>();
        examDTOS.add(examDTO);

        when(examDAO.findAll()).thenReturn(exams);
        when(examDTOMapper.apply(exam)).thenReturn(examDTO);
        assertEquals(examDTOS, examDAO.findAll().stream().map(examDTOMapper).collect(Collectors.toSet()));
    }

    @Test
    void saveExam() {
        examDAO.save(examService.convertToEntity(examDTO));
        when(examService.convertToDto(exam)).thenReturn(examDTO);
        assertEquals(examDTO, examService.convertToDto(exam));
    }

    @Test
    void findOneByIsActiveFalse() {
        ExamDTO examDTO = ExamDTO.builder().id(1).isActive(false).build();
        assertFalse(examDTO.isActive());
    }


    @Test
    void findByCategoryIdAndIsActive() {
        List<Exam> exams = new ArrayList<>();
        exams.add(exam);
        List<ExamDTO> examDTOS = new ArrayList<>();
        examDTOS.add(examDTO);

        when(examDAO.findByCategoryIdAndIsActive(3, true)).thenReturn(exams);
        when(examDTOMapper.apply(exam)).thenReturn(examDTO);
        assertEquals(examDTOS, examDAO.findByCategoryIdAndIsActive(3, true).stream().map(examDTOMapper).collect(Collectors.toList()));
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
}