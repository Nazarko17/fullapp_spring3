package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.dtos.QuestionDTO;
import com.example.fullapp_spring3.services.ExamService;
import com.example.fullapp_spring3.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final ExamService examService;

    @GetMapping("/{questionId}")
    public QuestionDTO findQuestion(@PathVariable("questionId") int questionId) {
        return questionService.findQuestion(questionId);
    }

    @PostMapping("/")
    public ResponseEntity<QuestionDTO> saveQuestion(@RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.saveQuestion(questionDTO));
    }

    @PutMapping("/")
    public ResponseEntity<QuestionDTO> updateQuestion(@RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.updateQuestion(questionDTO));
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") int questionId) {
        questionService.deleteQuestion(questionId);
    }

    @GetMapping("/exam/all/{examId}")
    public ResponseEntity<?> finQuestionsByExamAsAdmin(@PathVariable ("examId") int id) {
        return ResponseEntity.ok(questionService.finQuestionsByExamAsAdmin(id));
    }

    @PostMapping("/evaluate-exam")
    public ResponseEntity<?> evaluateExam(@RequestBody List<QuestionDTO> questionsDTO) {
        return ResponseEntity.ok(questionService.evaluateExam(questionsDTO));
    }
}
