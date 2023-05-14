package com.example.fullapp_spring3.daos;

import com.example.fullapp_spring3.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
