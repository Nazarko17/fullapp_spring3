package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.models.Exam;
import com.example.fullapp_spring3.services.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping("/")
    public ResponseEntity<Exam> saveExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(examService.addExam(exam));
    }

    @PutMapping("/")
    public ResponseEntity<Exam> updateExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(examService.updateExam(exam));
    }

    @GetMapping("/")
    public ResponseEntity<?> findExams() {
        return ResponseEntity.ok(examService.findExams());
    }

    @GetMapping("/{examId}")
    public Exam findExamById(@PathVariable("examId") int id) {
        return examService.getExam(id);
    }

    @DeleteMapping("/{examId}")
    public void deleteExam(@PathVariable("examId") int id) {
        examService.deleteExam(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<Exam> examListByCategory(@PathVariable("categoryId") int categoryId) {
//        Category category = new Category();
//        category.setId(categoryId);
        return examService.examListByCategoryId(categoryId);
    }

    @GetMapping("/active")
    public List<Exam> findByActive() {
        return examService.findByActive();
    }

    @GetMapping("/category/active/{categoryId}")
    public List<Exam> findByCategoryAndActive(@PathVariable("categoryId") int categoryId) {
//        Category category = new Category();
//        category.setId(categoryId);
        return examService.findByCategoryIdAndIsActive(categoryId);
    }
}
