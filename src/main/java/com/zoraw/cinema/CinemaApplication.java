package com.zoraw.cinema;

import com.zoraw.cinema.config.ReservationProperties;
import com.zoraw.cinema.config.TicketProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({TicketProperties.class, ReservationProperties.class})
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

}
