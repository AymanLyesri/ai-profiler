package com.example.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Topic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne
    private History history;
}