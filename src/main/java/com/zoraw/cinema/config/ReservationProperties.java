package com.zoraw.cinema.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix="reservation")
public class ReservationProperties {

    private int minimumMinutesBeforeScreening;
}
