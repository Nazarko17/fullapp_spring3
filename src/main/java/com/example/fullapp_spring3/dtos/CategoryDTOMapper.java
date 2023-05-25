package com.example.fullapp_spring3.dtos;

import com.example.fullapp_spring3.models.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {
    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getTitle(),
                category.getDescription(),
                category.getNumberOfExams()
        );
    }
}
