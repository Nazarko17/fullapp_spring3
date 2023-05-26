package com.example.fullapp_spring3.services;

import com.example.fullapp_spring3.daos.CategoryDAO;
import com.example.fullapp_spring3.dtos.CategoryDTO;
import com.example.fullapp_spring3.dtos.CategoryDTOMapper;
import com.example.fullapp_spring3.models.Category;
import com.example.fullapp_spring3.models.Exam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
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
        Set<Exam> exams = Set.of(
                Exam.builder().id(1).build(),
                Exam.builder().id(2).build(),
                Exam.builder().id(3).build());
        category = Category.builder().id(1).title("Test")
                .description("Description").numberOfExams(0).exams(exams).build();
        categoryDTO = CategoryDTO.builder().id(1).title("Test")
                .description("Description").numberOfExams(0).build();
    }

    @Test
    void findCategory() {
        when(categoryService.findCategory(1)).thenReturn(categoryDTO);
        CategoryDTO categoryDTO1 = categoryService.findCategory(1);
        assertThat(categoryDTO1).isNotNull();
        assertThat(categoryDTO1.getId()).isEqualTo(1);
    }

    @Test
    void findCategories() {
        List<Category> categories = List.of(category);
        Set<CategoryDTO> categoryDTOS = Set.of(categoryDTO);

        when(categoryDAO.findAll()).thenReturn(categories);
        when(categoryDTOMapper.apply(category)).thenReturn(categoryDTO);
        assertThat(categoryDTOS).isNotNull();
        assertThat(categoryDTOS).size().isEqualTo(1);
        assertEquals(categoryDTOS, categoryDAO.findAll().stream().map(categoryDTOMapper).collect(Collectors.toSet()));
    }

    @Test
    void saveCategory() {
        when(categoryService.saveCategory(categoryDTO)).thenReturn(categoryDTO);
        CategoryDTO categoryDTO1 = categoryService.saveCategory(categoryDTO);
        assertThat(categoryDTO1).isNotNull();
        assertThat(categoryDTO1.getId()).isGreaterThan(0);
        assertThat(categoryDTO1.getNumberOfExams()).isEqualTo(0);
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

    @Test
    void findNumberOfExams() {
        assertThat(category.getExams().size()).isEqualTo(3);
    }
}