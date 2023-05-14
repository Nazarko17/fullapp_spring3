package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.models.Category;
import com.example.fullapp_spring3.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") int id) {
        return categoryService.getCategory(id);
    }

    @GetMapping("/")
    public ResponseEntity<?> listOfCategories() {
        return ResponseEntity.ok(categoryService.findCategories());
    }

    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") int id) {
        categoryService.deleteCategory(id);
    }
}
