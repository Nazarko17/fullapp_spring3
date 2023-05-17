package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.ExamDAO;
import com.example.fullapp_spring3.dtos.ExamDTO;
import com.example.fullapp_spring3.dtos.ExamDTOMapper;
import com.example.fullapp_spring3.models.Exam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamDAO examDAO;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final ExamDTOMapper examDTOMapper;

    public ExamDTO findExam(int id) {
        return examDTOMapper.apply(examDAO.findById(id).get());
    }

    public Set<ExamDTO> findExams() {
        return new LinkedHashSet<>(examDAO.findAll().stream().map(examDTOMapper).collect(Collectors.toSet()));
    }

    public ExamDTO saveExam(ExamDTO examDTO) {
        Exam exam = examDAO.save(convertToEntity(examDTO));
        ExamDTO examDTO2 = convertToDto(exam);
        examDTO2.setCategoryDTO(categoryService.findCategory(examDTO.getCategoryDTO().getId()));
        return examDTO2;
    }

    public ExamDTO updateExam(ExamDTO examDTO) {
        Exam exam = examDAO.save(convertToEntity(examDTO));
        ExamDTO examDTO2 = convertToDto(exam);
        examDTO2.setCategoryDTO(categoryService.findCategory(examDTO.getCategoryDTO().getId()));
        return examDTO2;
    }

    public void deleteExam(int id) {
        Exam exam = new Exam();
        exam.setId(id);
        examDAO.delete(exam);
    }

    public List<ExamDTO> findByIsActive() {

        return examDAO.findByIsActive(true).stream().map(examDTOMapper).collect(Collectors.toList());
    }

    public List<ExamDTO> findByCategoryId(int id) {
        return examDAO.findByCategoryId(id).stream().map(examDTOMapper).collect(Collectors.toList());
    }

    public List<ExamDTO> findByCategoryIdAndIsActive(int id) {
        return examDAO.findByCategoryIdAndIsActive(id, true).stream().map(examDTOMapper).collect(Collectors.toList());
    }

    public ExamDTO convertToDto(Exam exam) {
        return modelMapper.map(exam, ExamDTO.class);
    }

    public Exam convertToEntity(ExamDTO examDTO) {
        return modelMapper.map(examDTO, Exam.class);
    }
}
