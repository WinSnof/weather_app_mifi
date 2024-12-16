package com.nejas.weather;

import com.nejas.weather.domain.Forecast;
import com.nejas.weather.domain.ForecastInfo;
import com.nejas.weather.service.WeatherService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Slf4j
public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        WeatherService weatherService = new WeatherService();

        while (true) {
            Double lat = null;
            Double lon = null;
            Integer limit = null;
            log.info("Type any char if you want to skip value.");
            log.info("Type lat:");
            if (scanner.hasNextDouble()) {
                lat = Math.min(scanner.nextDouble(), 90);
            } else {
                scanner.nextLine();
            }
            log.info("Type lon:");
            if (scanner.hasNextDouble()) {
                lon = scanner.nextDouble();
            }else {
                scanner.nextLine();
            }
            log.info("Type limit:");
            if (scanner.hasNextInt()) {
                limit = scanner.nextInt();

                if(limit > 11) {
                    limit = 11;
                    log.info("Limit max value is 11");
                } else if (limit < 0) {
                    limit = 0;
                    log.info("Limit can't be less then 0");
                }
            }else {
                scanner.nextLine();
            }

            ForecastInfo foreCastInfo = weatherService.getForeCastInfo(lat, lon, limit);
            double i = weatherService.calculateAvgTemp(foreCastInfo.getForecasts());
            log.info("Average temp for {} days is: {}", limit == null ? 7 : limit, i);
        }

    }

}
