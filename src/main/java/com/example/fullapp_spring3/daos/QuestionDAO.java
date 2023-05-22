package com.example.fullapp_spring3.daos;

import com.example.fullapp_spring3.models.Exam;
import com.example.fullapp_spring3.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface QuestionDAO extends JpaRepository<Question, Integer> {

    Set<Question> findByExam(Exam exam);

    @Query("select q from Question q where q.id=:id")
    Question findById(int id);
}
