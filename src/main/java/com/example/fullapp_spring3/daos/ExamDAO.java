package com.example.fullapp_spring3.daos;

import com.example.fullapp_spring3.models.Category;
import com.example.fullapp_spring3.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamDAO extends JpaRepository<Exam, Integer> {
    List<Exam> findByCategory(Category category);

    List<Exam> findByIsActive(boolean isActive);

    List<Exam> findByCategoryAndIsActive(Category category, boolean isActive);
}
