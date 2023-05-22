package com.example.fullapp_spring3.daos;

import com.example.fullapp_spring3.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.id=:id")
    Category findById(int id);

}
