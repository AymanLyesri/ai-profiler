package com.example.backend.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import static com.example.backend.security.EnvLoader.loadEnv;

@Service
public class AiService {
    public static CompletableFuture<String> getCompletion(String prompt) throws IOException {
        // Set the model to use
        String model = "gpt-3.5-turbo";

        Properties env = loadEnv();
        String apiKey = env.getProperty("OPENAI_API_KEY");

        // Set the API endpoint URL
        String apiUrl = "https://api.openai.com/v1/chat/completions";

        // Create a JsonObject for the "messages" array element
        JsonObject requestBodyObject = getJsonObject(model, prompt);

        // Convert the JsonObject to a JSON string
        String requestBody = new Gson().toJson(requestBodyObject);

        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create an HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send the HTTP request asynchronously
        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // Process the response when it arrives and return the result
        return responseFuture.thenApply(response -> {
            int statusCode = response.statusCode();
            String responseBody = response.body();

            // Handle the response based on the status code
            if (statusCode == 200) {
                System.out.println("Recommendation request successful:");
                System.out.println(extractContentFromResponse(responseBody));

                // Extract content from the response
                // Return the content
                return extractContentFromResponse(responseBody);
            } else {
                System.err.println("Error: " + statusCode);
                System.err.println(responseBody);
                throw new RuntimeException("Failed to fetch recommendations. Status code: " + statusCode);
            }
        });
    }

    private static JsonObject getJsonObject(String model, String prompt) {
        JsonObject message = new JsonObject();
        message.addProperty("role", "user");
        message.addProperty("content", prompt);

        // Create a JsonArray and add the message JsonObject to it
        JsonArray messagesArray = new JsonArray();
        messagesArray.add(message);

        // Create the main JsonObject for the request body
        JsonObject requestBodyObject = new JsonObject();
        requestBodyObject.addProperty("model", model);
        requestBodyObject.add("messages", messagesArray);
        return requestBodyObject;
    }

    private static String extractContentFromResponse(String responseBody) {
        // Parse the JSON response and extract the "content" field
        JsonObject jsonResponse = new Gson().fromJson(responseBody, JsonObject.class);
        JsonArray choicesArray = jsonResponse.getAsJsonArray("choices");
        JsonObject firstChoice = choicesArray.get(0).getAsJsonObject();
        JsonObject messageObject = firstChoice.getAsJsonObject("message");
        return messageObject.get("content").getAsString();
    }
}
