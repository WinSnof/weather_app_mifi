package com.nejas.weather.domain;

import lombok.*;

@Data
public class Parts {
    private Part day;
    private Part evening;
    private Part morning;
    private Part night;
}