package com.example.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class History {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy = "history")
    private Topic topic;
    @OneToMany(mappedBy = "history")
    private List<Interaction> interactions;
    @OneToMany(mappedBy = "history")
    private List<Purchase> purchases;
    @OneToMany(mappedBy = "history")
    private List<Coordinate> coordinates;
    @ManyToOne
    private User user;
}
