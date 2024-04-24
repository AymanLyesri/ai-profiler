package com.example.backend.model;

import jakarta.persistence.*;


@Entity
public class Profile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @OneToOne
    private User user;
}