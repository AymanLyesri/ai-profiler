package com.example.backend.controller;

import com.example.backend.model.History;
import com.example.backend.model.Response;
import com.example.backend.model.User;
import com.example.backend.service.HistoryService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryController {

    @Autowired
    private UserService userService;
    @Autowired
    private HistoryService historyService;

    // upload history, accepts user that has an added history and saves it
    @PostMapping("/history/upload")
    public Response uploadHistory(@RequestBody UploadRequest request) {
        // Upload history to database

        System.out.println("Received User object: " + request.user.toString() + "Received History object: " + request.history.toString());

        User user = userService.getUserById(request.user.getId());
        History history = request.history;

        historyService.addHistory(user, history);

        return new Response(200, "History uploaded successfully");
    }

    public static class UploadRequest {
        public User user;
        public History history;

    }
}
