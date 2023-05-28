package com.example.fullapp_spring3.daos;

import com.example.fullapp_spring3.models.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamResultDAO extends JpaRepository<ExamResult, Integer> {
}
