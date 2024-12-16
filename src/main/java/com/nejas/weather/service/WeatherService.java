package com.nejas.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nejas.weather.client.YandexClient;
import com.nejas.weather.domain.Forecast;
import com.nejas.weather.domain.ForecastInfo;

import java.util.List;

public class WeatherService {

    private final YandexClient yandexClient = new YandexClient();
    private final ObjectMapper objectMapper;

    public WeatherService() {
        ObjectMapper config = new ObjectMapper();
        config.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper = config;
    }

    public ForecastInfo getForeCastInfo(Double lat, Double lon, Integer limit) {
        try {

            String data = yandexClient.getData(lat, lon, limit);
            return data != null ? objectMapper.readValue(data, ForecastInfo.class) : null;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public double calculateAvgTemp(List<Forecast> forecasts) {
        double avg = 0;
        for (Forecast forecast : forecasts) {
            int tempDay = forecast.getParts().getNight().getTempAvg();
            tempDay += forecast.getParts().getMorning().getTempAvg();
            tempDay += forecast.getParts().getDay().getTempAvg();
            tempDay += forecast.getParts().getEvening().getTempAvg();
            avg += (double) tempDay / 4;
        }
        return avg / forecasts.size();
    }

}
