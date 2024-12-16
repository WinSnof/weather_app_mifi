package com.nejas.weather.client;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

@Slf4j
public class YandexClient {

    private final String key;
    private final String url;
    private final HttpClient client = HttpClient.newHttpClient();

    public YandexClient() {
        ResourceBundle config = ResourceBundle.getBundle("application");
        this.key = config.getString("yandex.key");
        this.url = "https://api.weather.yandex.ru/v2/forecast";
    }

    public String getData(Double lat, Double lon, Integer limit) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(buildURI(lat, lon, limit)))
                .GET()
                .setHeader("X-Yandex-Weather-Key", key)
                .build();

        try {
            log.debug("Trying to request resource {}", request.uri());
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Yandex body response is: {}", response.body());

            if (response.statusCode() < 300) {
                return response.body();
            }

        } catch (InterruptedException | IOException e) {
            System.out.println("Error");
        }

        return null;
    }

    private String buildURI(Double lat, Double lon, Integer limit) {
        StringBuilder builder = new StringBuilder(url);

        builder.append("?");

        if (lat != null) {
            builder.append("&lat=");
            builder.append(lat);
        }

        if (lon != null) {
            builder.append("&lon=");
            builder.append(lon);
        }

        if (limit != null) {
            builder.append("&limit=");
            builder.append(limit);
        }

        return builder.toString();
    }


}
