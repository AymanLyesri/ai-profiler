package com.example.backend.controller;

import com.example.backend.model.History;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryController {

    @Autowired
    private UserService userService;

    // add history
    @GetMapping("/history")
    public void addHistory() {
        // Add history to database

        User user = new User();
        user.setId(1);

        History history = new History();

        userService.addHistoryToUser(user, history);


    }
}
