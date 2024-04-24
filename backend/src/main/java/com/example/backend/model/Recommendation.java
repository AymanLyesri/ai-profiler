package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class Recommendation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @OneToOne(mappedBy = "recommendation")
    private Rating rating;
}
