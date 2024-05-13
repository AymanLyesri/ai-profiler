package com.example.backend.controller;

import com.example.backend.model.History;
import com.example.backend.model.Response;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticController {

    @Autowired
    private UserService userService;

    @PostMapping("/statistic/interaction/size")
    public Response getHistories(@RequestBody int userId) {
        // Get user by id
        User user = userService.getUserById(userId);
        // Get all histories
        List<History> histories = user.getHistories();
        // get the size of the interactions
        int interactionsSize = histories.stream().mapToInt(history -> history.getInteractions().size()).sum();
        return new Response<>(200, interactionsSize);
    }

    @PostMapping("/statistic/purchase/size")
    public Response getPurchaseSize(@RequestBody int userId) {
        // Get user by id
        User user = userService.getUserById(userId);
        // Get all histories
        List<History> histories = user.getHistories();
        // get the size of the purchases
        int purchasesSize = histories.stream().mapToInt(history -> history.getPurchases().size()).sum();
        return new Response<>(200, purchasesSize);
    }

    @PostMapping("/statistic/coordinate/size")
    public Response getCoordinateSize(@RequestBody int userId) {
        // Get user by id
        User user = userService.getUserById(userId);
        // Get all histories
        List<History> histories = user.getHistories();
        // get the size of the coordinates
        int coordinatesSize = histories.stream().mapToInt(history -> history.getCoordinates().size()).sum();
        return new Response<>(200, coordinatesSize);
    }

    @PostMapping("/statistic/recommendation/size")
    public Response getRecommendationSize(@RequestBody int userId) {
        // Get user by id
        User user = userService.getUserById(userId);
        // Get all histories
        List<History> histories = user.getHistories();
        // get the size of the recommendations
        int recommendationsSize = histories.stream().mapToInt(history -> history.getRecommendation() != null ? 1 : 0).sum();
        return new Response<>(200, recommendationsSize);
    }

}
