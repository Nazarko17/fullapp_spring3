package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.CategoryDAO;
import com.example.fullapp_spring3.dtos.CategoryDTO;
import com.example.fullapp_spring3.dtos.CategoryDTOMapper;
import com.example.fullapp_spring3.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CategoryDAO categoryDAO;
    @Mock
    private CategoryDTOMapper categoryDTOMapper;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        category = Category.builder().id(1).title("Test")
                .description("Description").build();
        categoryDTO = new CategoryDTO(1, "Test",
                "Description");
    }
    @Test
    void findCategory() {
        when(categoryDAO.findById(1)).thenReturn(category);
        when(categoryDTOMapper.apply(category)).thenReturn(categoryDTO);
        assertEquals(categoryDTO, categoryService.findCategory(1));

    }
    @Test
    void findCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        LinkedHashSet<CategoryDTO> categoryDTOS = new LinkedHashSet<>();
        categoryDTOS.add(categoryDTO);

        when(categoryDAO.findAll()).thenReturn(categories);
        when(categoryDTOMapper.apply(category)).thenReturn(categoryDTO);
        assertEquals(categoryDTOS, categoryDAO.findAll().stream().map(categoryDTOMapper).collect(Collectors.toSet()));
    }

    @Test
    void saveCategory() {
        categoryDAO.save(categoryService.convertToEntity(categoryDTO));
        when(categoryService.convertToDto(category)).thenReturn(categoryDTO);
        assertEquals(categoryDTO, categoryService.convertToDto(category));
    }
    @Test
    void convertToDto() {
        when(modelMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);
        assertEquals(categoryDTO, categoryService.convertToDto(category));
    }
    @Test
    void convertToEntity() {
        when(modelMapper.map(categoryDTO, Category.class)).thenReturn(category);
        assertEquals(category, categoryService.convertToEntity(categoryDTO));
    }
}