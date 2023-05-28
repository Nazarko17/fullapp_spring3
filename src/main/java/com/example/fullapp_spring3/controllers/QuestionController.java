package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.dtos.ExamResultDTO;
import com.example.fullapp_spring3.dtos.QuestionDTO;
import com.example.fullapp_spring3.models.ExamResult;
import com.example.fullapp_spring3.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{questionId}")
    public QuestionDTO findQuestion(@PathVariable("questionId") int questionId) {
        return questionService.findQuestion(questionId);
    }

    @GetMapping("/exam/all/{examId}")
    public ResponseEntity<?> finQuestionsByExamAsAdmin(@PathVariable ("examId") int id) {
        return ResponseEntity.ok(questionService.finQuestionsByExamAsAdmin(id));
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

    @PostMapping("/evaluate-exam")
    public ResponseEntity<?> evaluateExam(@RequestBody List<QuestionDTO> questionsDTO, Principal principal) {
        return ResponseEntity.ok(questionService.evaluateExam(questionsDTO, principal));
    }
}
