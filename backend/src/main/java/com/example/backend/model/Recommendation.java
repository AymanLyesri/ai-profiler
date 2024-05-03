package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String recommendation;
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

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
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
