package com.example.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy = "history")
    private Topic topic;
    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL)
    private List<Interaction> interactions;
    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL)
    private List<Purchase> purchases;
    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL)
    private List<Coordinate> coordinates;
    @OneToOne(mappedBy = "history", cascade = CascadeType.ALL)
    private Recommendation recommendation;
    @ManyToOne
    private User user;

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
