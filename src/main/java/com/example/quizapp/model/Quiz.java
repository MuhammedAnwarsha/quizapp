package com.example.quizapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToMany
    private List<Question>questions;

}
