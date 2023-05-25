package com.example.fullapp_spring3.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    int id;
    String title;
    String description;
    int numberOfExams;
}
