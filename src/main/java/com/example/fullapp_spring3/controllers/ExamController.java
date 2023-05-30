package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.dtos.ExamDTO;
import com.example.fullapp_spring3.dtos.ExamResultDTO;
import com.example.fullapp_spring3.services.ExamResultService;
import com.example.fullapp_spring3.services.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;
    private final ExamResultService examResultService;

    @GetMapping("/{examId}")
    public ExamDTO findExamById(@PathVariable("examId") int id) {
        return examService.findExam(id);
    }

    @GetMapping("/")
    public ResponseEntity<Set<ExamDTO>> findExams() {
        return ResponseEntity.ok(examService.findExams());
    }

    @PostMapping("/")
    public ResponseEntity<ExamDTO> saveExam(@RequestBody ExamDTO examDTO) {
        return ResponseEntity.ok(examService.saveExam(examDTO));
    }

    @PutMapping("/")
    public ResponseEntity<ExamDTO> updateExam(@RequestBody ExamDTO examDTO) {
        return ResponseEntity.ok(examService.updateExam(examDTO));
    }

    @DeleteMapping("/{examId}")
    public void deleteExam(@PathVariable("examId") int id) {
        examService.deleteExam(id);
    }


    @GetMapping("/active")
    public List<ExamDTO> findByActive() {
        return examService.findByIsActive();
    }

    @GetMapping("/category/{categoryId}")
    public List<ExamDTO> findByCategoryId(@PathVariable("categoryId") int categoryId) {
        return examService.findByCategoryId(categoryId);
    }

    @GetMapping("/category/active/{categoryId}")
    public List<ExamDTO> findByCategoryIdAndIsActive(@PathVariable("categoryId") int categoryId) {
        return examService.findByCategoryIdAndIsActive(categoryId);
    }

    @GetMapping("/results")
    public ResponseEntity<Set<ExamResultDTO>> findExamResults() {
        return ResponseEntity.ok(examResultService.findExamResults());
    }

    @DeleteMapping("/results/delete")
    public void deleteExamResults() {
        examResultService.deleteExamResults();
    }
}
