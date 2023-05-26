package com.example.fullapp_spring3.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    int id;
    String title;
    String description;
    int numberOfExams;
}
