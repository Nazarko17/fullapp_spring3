package com.example.fullapp_spring3.daos;

import com.example.fullapp_spring3.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamDAO extends JpaRepository<Exam, Integer> {

    List<Exam> findByIsActive(boolean isActive);

    List<Exam> findByCategoryId(int id);

    List<Exam> findByCategoryIdAndIsActive(int id, boolean isActive);
}
