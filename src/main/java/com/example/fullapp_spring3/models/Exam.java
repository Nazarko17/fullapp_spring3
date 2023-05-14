package com.example.fullapp_spring3.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exams")
public class Exam {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String title;
    private String description;
    private String maxPoints;
    private String numberOfQuestions;
    @JsonProperty("isActive")
    private boolean isActive = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

}
