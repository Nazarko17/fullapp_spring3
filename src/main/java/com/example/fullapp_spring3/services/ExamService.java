package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.ExamDAO;
import com.example.fullapp_spring3.models.Category;
import com.example.fullapp_spring3.models.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamService {

    @Autowired
    private ExamDAO examDAO;

    public Exam addExam(Exam exam) {
        return examDAO.save(exam);
    }

    public Exam updateExam(Exam exam) {
        return examDAO.save(exam);
    }

    public Set<Exam> findExams() {
        return new LinkedHashSet<>(examDAO.findAll());
    }

    public Exam getExam(int id) {
        return examDAO.findById(id).get();
    }

    public void deleteExam(int id) {
        Exam exam = new Exam();
        exam.setId(id);
        examDAO.delete(exam);
    }

    public List<Exam> examListByCategory(Category category) {
        return examDAO.findByCategory(category);
    }

    public List<Exam> findByActive() {
        return examDAO.findByIsActive(true);
    }

    public List<Exam> findByCategoryAndActive(Category category) {
        return examDAO.findByCategoryAndIsActive(category, true);
    }
}
