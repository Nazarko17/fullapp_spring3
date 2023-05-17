package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.CategoryDAO;
import com.example.fullapp_spring3.dtos.CategoryDTO;
import com.example.fullapp_spring3.dtos.CategoryDTOMapper;
import com.example.fullapp_spring3.models.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryDAO categoryDAO;
    private final CategoryDTOMapper categoryDTOMapper;

    public CategoryDTO findCategory(int id) {
        return categoryDTOMapper.apply(categoryDAO.findById(id).get());
    }

    public Set<CategoryDTO> findCategories() {
        return new LinkedHashSet<>(categoryDAO.findAll().stream().map(categoryDTOMapper).collect(Collectors.toSet()));
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = categoryDAO.save(convertToEntity(categoryDTO));
        return convertToDto(category);
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category = categoryDAO.save(convertToEntity(categoryDTO));
        return convertToDto(category);
    }

    public void deleteCategory(int id) {
        Category category = new Category();
        category.setId(id);
        categoryDAO.delete(category);
    }

    public CategoryDTO convertToDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }
}
