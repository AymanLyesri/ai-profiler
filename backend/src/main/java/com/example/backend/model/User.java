package com.example.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<History> histories;
    @OneToMany(mappedBy = "user")
    private List<Recommendation> recommendations;
    @OneToOne(mappedBy = "user")
    private Profile profile;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
