package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int verdict;
    @OneToOne
    private Recommendation recommendation;
}