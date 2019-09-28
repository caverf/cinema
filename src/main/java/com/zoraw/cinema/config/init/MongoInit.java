package com.zoraw.cinema.config.init;

import com.zoraw.cinema.model.db.mongo.ScreeningRepository;
import com.zoraw.cinema.model.db.mongo.dao.Movie;
import com.zoraw.cinema.model.db.mongo.dao.Room;
import com.zoraw.cinema.model.db.mongo.dao.Screening;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;

@RequiredArgsConstructor
@Profile("init")
@Component
public class MongoInit implements CommandLineRunner {

    private final ScreeningRepository screeningRepository;

    @Override
    public void run(String... args) {

        screeningRepository.save(Screening.builder()
                .movie(Movie.builder()
                        .title("Interstellar")
                        .build())
                .time(LocalDateTime.of(2020, Month.JANUARY, 1, 20, 0))
                .room(Room.builder()
                        .name("small")
                        .seats(new HashSet<>()).build())
                .build());
    }
}



