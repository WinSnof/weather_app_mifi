package com.nejas.weather.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class Part {
    @JsonProperty("temp_avg")
    private int tempAvg;
}