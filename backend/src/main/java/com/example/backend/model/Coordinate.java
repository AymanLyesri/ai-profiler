package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class Coordinate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city;
    private String country;
    @ManyToOne
    private History history;
}