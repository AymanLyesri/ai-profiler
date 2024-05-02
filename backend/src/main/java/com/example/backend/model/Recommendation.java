package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy = "recommendation")
    private Rating rating;
    @OneToOne
    private History history;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
