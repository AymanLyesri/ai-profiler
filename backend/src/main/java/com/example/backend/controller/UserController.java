package com.example.backend.controller;

import com.example.backend.model.Response;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            user.setPassword(null);
        }
        return new Response<>(200, users);
    }

    @GetMapping("/users/{id}")
    public Response getUserById(@PathVariable int id) {
        return new Response<>(200, userService.getUserById(id));
    }

    @PostMapping("/users/create")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // list histories
    @GetMapping("/users/histories")
    public Response getUserHistories(@RequestBody User user) {
        return new Response<>(200, user.getHistories());
    }
}