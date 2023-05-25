package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.ExamDAO;
import com.example.fullapp_spring3.dtos.ExamDTO;
import com.example.fullapp_spring3.dtos.ExamDTOMapper;
import com.example.fullapp_spring3.models.Category;
import com.example.fullapp_spring3.models.Exam;
import com.example.fullapp_spring3.models.Question;
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
    private final ModelMapper modelMapper;
    private final ExamDTOMapper examDTOMapper;
    private final CategoryService categoryService;

    public ExamDTO findExam(int id) {
        return examDTOMapper.apply(examDAO.findById(id));
    }

    public Set<ExamDTO> findExams() {
        return new LinkedHashSet<>(examDAO.findAll().stream().map(examDTOMapper).collect(Collectors.toSet()));
    }

    public ExamDTO saveExam(ExamDTO examDTO) {
        Exam exam = examDAO.save(convertToEntity(examDTO));
        ExamDTO examDTO2 = convertToDto(exam);
        int categoryID = examDTO.getCategoryDTO().getId();
        examDTO2.setCategoryDTO(categoryService.findCategory(categoryID));
        categoryService.saveNumberOfExams(categoryID);
        return examDTO2;
    }

    public ExamDTO updateExam(ExamDTO examDTO) {
        Exam exam = examDAO.save(convertToEntity(examDTO));
        ExamDTO examDTO2 = convertToDto(exam);
        examDTO2.setCategoryDTO(categoryService.findCategory(examDTO.getCategoryDTO().getId()));
        return examDTO2;
    }

    public void deleteExam(int id) {
        Category category = examDAO.findById(id).getCategory();
        examDAO.deleteById(id);
        categoryService.saveNumberOfExams(category.getId());
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

    public Exam saveMaxPointsAndNumberOfQuestions(int id) {
        Exam exam = examDAO.findById(id);
        exam.setMaxPoints(findMaxPoints(id));
        exam.setNumberOfQuestions(findNumberOfQuestions(id));
        return examDAO.save(exam);
    }

    public int findMaxPoints(int id) {
        return examDAO.findById(id).getQuestions().stream()
                .mapToInt(Question::getPoints)
                .sum();
    }

    public int findNumberOfQuestions(int id) {
        return examDAO.findById(id).getQuestions().size();
    }

    public int calculatePointsToPass(int id) {
        return (examDAO.findById(id).getPassPercentage() * findMaxPoints(id)) / 100;
    }
}