package com.example.demo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


 


@RestController
public class DemoController {

    @PostMapping("/get-prediction")
    public String getPrediction(@RequestBody double[] input) {
        try {
            // Create HTTP client
            HttpClient client = HttpClient.newHttpClient();

            // Create JSON body
            String json = "{\"input\": " + Arrays.toString(input) + "}";

            // Create request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:5000/predict"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            // Send request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return "Model says: " + response.body();

        } catch (Exception e) {
            return "Oops: " + e.getMessage();
        }
    }
}
