package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.CategoryDAO;
import com.example.fullapp_spring3.models.Category;
import com.example.fullapp_spring3.models.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;


    public Category addCategory(Category category) {
        return categoryDAO.save(category);
    }

    public Category updateCategory(Category category) {
        return categoryDAO.save(category);
    }

    public Set<Category> findCategories() {
        return new LinkedHashSet<>(categoryDAO.findAll());
    }

    public Category getCategory(int id) {
        return categoryDAO.findById(id).get();
    }

    public void deleteCategory(int id) {
        Category category = new Category();
        category.setId(id);
        categoryDAO.delete(category);
    }
}
