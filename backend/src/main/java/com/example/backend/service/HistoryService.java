package com.example.backend.service;

import com.example.backend.model.History;
import com.example.backend.model.Interaction;
import com.example.backend.model.User;
import com.example.backend.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    // Get all history of a user
    public void getAllHistory(int userId) {
        // Get all history of a user from database
        historyRepository.findAll();
    }

    public void addHistory(User user, History history) {
        // Add the history to the user's list of histories
//        user.getHistories().add(history);
        // Set the user for the history record
        history.setUser(user);

        // Iterate over interactions and set the history for each interaction
        List<Interaction> interactions = history.getInteractions();
        if (interactions != null) {
            for (Interaction interaction : interactions) {
                interaction.setHistory(history);
                // Optionally, you can save each interaction as well if needed
                // interactionRepository.save(interaction);
            }
        }

        // Save the updated user entity to update the relationship
//        userRepository.save(user);

        // Save the history entity
        historyRepository.save(history);
    }
}
