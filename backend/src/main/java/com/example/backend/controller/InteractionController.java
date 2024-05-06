package com.example.backend.controller;

import com.example.backend.model.History;
import com.example.backend.model.Interaction;
import com.example.backend.model.Recommendation;
import com.example.backend.model.Response;
import com.example.backend.repository.InteractionRepository;
import com.example.backend.repository.RecommendationRepository;
import com.example.backend.service.AiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class InteractionController {
    private final InteractionRepository interactionRepository;
    private final RecommendationRepository recommendationRepository;

    public InteractionController(InteractionRepository interactionRepository, RecommendationRepository recommendationRepository) {
        this.interactionRepository = interactionRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @PostMapping("/interaction/recommendation")
    public CompletableFuture<Response> interactionRecommendation(@RequestBody int historyId) throws IOException {
        System.out.println("Received History ID: " + historyId);
        // Get interactions with history id
        List<Interaction> interactions = interactionRepository.findByHistoryId(historyId);
        interactions = interactions.subList(0, Math.min(interactions.size(), 10));

        StringBuilder data = new StringBuilder("Generate a recommendation for new links and topics based on the links provided:\n");

        System.out.println("Interactions: " + interactions.toArray().length);
        for (Interaction interaction : interactions) {
            System.out.println(interaction.toData());
            data.append(interaction.toData()).append("\n");
        }

        // Call AI service to get recommendation asynchronously
        CompletableFuture<String> resultFuture = AiService.getCompletion(data.toString());

        // Map the completion of the resultFuture to construct the response
        return resultFuture.thenApply(content -> {
            // Remove backslashes from the content
            String cleanedContent = content.replace("\\", "");

            // Create a new history object
            History history = new History();
            history.setId(historyId);
            // Save the recommendation to the database
            Recommendation recommendation = new Recommendation();
            recommendation.setRecommendation(cleanedContent);
            recommendation.setHistory(history);
            recommendationRepository.save(recommendation);
            // Create and return the Response object
            return new Response(200, recommendation);
        });
    }

}
