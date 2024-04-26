package com.example.backend.service;

import com.example.backend.model.History;
import com.example.backend.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    // Get all history of a user
    public void getAllHistory(int userId) {
        // Get all history of a user from database
        historyRepository.findAll();
    }

    public void addHistory(History history) {
        // Add history to database
        historyRepository.save(history);


    }
}
