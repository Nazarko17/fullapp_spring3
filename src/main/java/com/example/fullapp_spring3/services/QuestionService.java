package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.QuestionDAO;
import com.example.fullapp_spring3.models.Exam;
import com.example.fullapp_spring3.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class QuestionService {
    @Autowired
    private QuestionDAO questionDAO;

    public Question addQuestion(Question question) {
        return questionDAO.save(question);
    }

    public Question updateQuestion(Question question) {
        return questionDAO.save(question);
    }

    public Set<Question> findQuestions() {
        return (Set<Question>) questionDAO.findAll();
    }

    public Question getQuestion(int id) {
        return questionDAO.findById(id).get();
    }

    public Set<Question> getExamQuestions(Exam exam) {
        return questionDAO.findByExam(exam);
    }

    public void deleteQuestion(int id) {
        Question question = new Question();
        question.setId(id);
        questionDAO.delete(question);
    }

    public Question listQuestion(int questionId) {
        return questionDAO.getOne(questionId);
    }
}
