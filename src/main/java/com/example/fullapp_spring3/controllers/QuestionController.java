package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.models.Exam;
import com.example.fullapp_spring3.models.Question;
import com.example.fullapp_spring3.services.ExamService;
import com.example.fullapp_spring3.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;

    @PostMapping("/")
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<?> finQuestionsByExam(@PathVariable("examId") int examId) {
        Exam exam = examService.getExam(examId);
        Set<Question> examQuestions = exam.getQuestions();

        List exams = new ArrayList(examQuestions);
        if(exams.size() > Integer.parseInt(exam.getNumberOfQuestions())) {
            exams = exams.subList(0, Integer.parseInt(exam.getNumberOfQuestions() + 1));
        }

        Collections.shuffle(exams);
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/{questionId}")
    public Question findQuestionById(@PathVariable("questionId") int questionId) {
        return questionService.getQuestion(questionId);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") int questionId) {
        questionService.deleteQuestion(questionId);
    }

    @GetMapping("/exam/all/{examId}")
    public ResponseEntity<?> finQuestionsByExamAsAdmin(@PathVariable ("examId") int id) {
        Exam exam = new Exam();
        exam.setId(id);
        Set<Question> questions = questionService.getExamQuestions(exam);
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/evaluate-exam")
    public ResponseEntity<?> evaluateExam(@RequestBody List<Question> questions) {
        double maxPoints = 0;
        int correctAnswers = 0;
        int attempts = 0;

        for(Question q : questions) {
            Question question = questionService.listQuestion(q.getId());
            if(question.getAnswer().equals(q.getCorrectAnswer())) {
                correctAnswers++;
                double points = Double.parseDouble(questions.get(0).getExam().getMaxPoints())/questions.size();
                maxPoints += points;
            }
            if(q.getCorrectAnswer() != null) {
                attempts++;
            }
        }

        Map<String, Object> answers = new HashMap<>();
        answers.put("maxPoints", maxPoints);
        answers.put("correctAnswers", correctAnswers);
        answers.put("attempts", attempts);
        return ResponseEntity.ok(answers);
    }
}
