package com.nejas.weather.domain;


import lombok.*;

import java.util.Date;

@Data
public class Forecast {
    private Date date;
    private Parts parts;
}
