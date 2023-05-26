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
        return categoryDTOMapper.apply(categoryDAO.findById(id));
    }

    public Set<CategoryDTO> findCategories() {
        return categoryDAO.findAll().stream().map(categoryDTOMapper).collect(Collectors.toCollection(LinkedHashSet::new));
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
        categoryDAO.deleteById(id);
    }

    public CategoryDTO convertToDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    public void saveNumberOfExams(int id) {
        Category category = categoryDAO.findById(id);
        category.setNumberOfExams(findNumberOfExams(id));
        categoryDAO.save(category);
    }

    public int findNumberOfExams(int id) {
        return categoryDAO.findById(id).getExams().size();
    }
}