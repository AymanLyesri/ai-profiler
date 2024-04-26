package com.example.backend.controller;

import com.example.backend.model.Response;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/api/login")
    public Response login(@RequestBody User user) {
        System.out.println("Received User object: " + user.toString());

        if (user.getUsername() != null && user.getPassword() != null) {
            if (user.getUsername().equals("admin") && user.getPassword().equals("admin")) {
                return new Response(200, userRepository.findByUsername(user.getUsername()));
            } else {
                return new Response(401, "Invalid username or password");
            }
        } else {
            return new Response(400, "Username or password cannot be null");
        }
    }
}
