package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.models.Category;
import com.example.fullapp_spring3.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
//        Category savedCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(categoryService.addCategory(category));
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
