package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.ExamResultDAO;
import com.example.fullapp_spring3.dtos.ExamResultDTO;
import com.example.fullapp_spring3.dtos.ExamResultDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamResultService {

    private final ExamResultDAO examResultDAO;
    private final ExamResultDTOMapper examResultDTOMapper;

    public Set<ExamResultDTO> findExamResults() {
        return examResultDAO.findAll().stream().map(examResultDTOMapper).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void deleteExamResults() {
        examResultDAO.deleteAll();
    }
}
