package com.example.fullapp_spring3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Builder
@Table(name = "categories")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    int numberOfExams = 0;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Exam> exams = new LinkedHashSet<>();

    public Category(int id) {
        this.id = id;
    }

    public Category(int id, String title, String description, int numberOfExams) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.numberOfExams = numberOfExams;
    }
}
