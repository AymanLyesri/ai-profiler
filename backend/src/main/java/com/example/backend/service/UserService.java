package com.example.backend.service;

import com.example.backend.model.History;
import com.example.backend.model.User;
import com.example.backend.repository.HistoryRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HistoryRepository historyRepository;

    public List<User> getAllUsers() {
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void addHistoryToUser(User user, History history) {
        // Add the history to the user's list of histories
//        user.getHistories().add(history);
        // Set the user for the history record
        history.setUser(user);

        // Save the updated user entity to update the relationship
//        userRepository.save(user);

        // Save the history entity
        historyRepository.save(history);
    }

    public User getUserById(int id) {
        return userRepository.findById((long) id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
