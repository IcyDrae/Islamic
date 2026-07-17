package com.IcyDrae;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {
    private final HttpClient client;

    public Request() {
        client = HttpClient.newHttpClient();
    }

    public String Get(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        HttpResponse<String> response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("HTTP Status: " + response.statusCode());

        return response.body();
    }
}
