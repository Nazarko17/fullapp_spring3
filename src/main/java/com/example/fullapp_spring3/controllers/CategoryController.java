package com.example.fullapp_spring3.controllers;

import com.example.fullapp_spring3.dtos.CategoryDTO;
import com.example.fullapp_spring3.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public CategoryDTO findCategory(@PathVariable("categoryId") int id) {
        return categoryService.findCategory(id);
    }

    @GetMapping("/")
    public ResponseEntity<Set<CategoryDTO>> findCategories() {
        return ResponseEntity.ok(categoryService.findCategories());
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.saveCategory(categoryDTO));
    }

    @PutMapping("/")
    public ResponseEntity<CategoryDTO>  updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") int id) {
        categoryService.deleteCategory(id);
    }
}
