package com.nejas.weather.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class ForecastInfo {
    private Timestamp now;
    @JsonProperty("now_dt")
    private Date nowDt;
    private Fact fact;
    private List<Forecast> forecasts;
}