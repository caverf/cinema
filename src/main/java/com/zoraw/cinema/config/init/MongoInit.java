package com.zoraw.cinema.config.init;

import com.zoraw.cinema.model.db.mongo.ScreeningRepository;
import com.zoraw.cinema.model.db.mongo.dao.Movie;
import com.zoraw.cinema.model.db.mongo.dao.Room;
import com.zoraw.cinema.model.db.mongo.dao.Screening;
import com.zoraw.cinema.model.db.mongo.dao.Seat;
import com.zoraw.cinema.model.dto.Edge;
import com.zoraw.cinema.util.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
@Profile("demo")
@Component
public class MongoInit implements CommandLineRunner {

    private final ScreeningRepository screeningRepository;

    @Override
    public void run(String... args) {
        create3RoomsWith3ScreeningOf3Movies();
    }

    private void create3RoomsWith3ScreeningOf3Movies() {
        String[] movies = {
                "Zażółć gęślą jaźń - w poszukiwaniu polskich liter",
                "Clean Code - the movie",
                "Film testowy nr 3"
        };
        Set<Seat> seatArrangement = getSeats4x5();
        Random random = RandomGenerator.getRandom();

        for (String movie : movies) {
            for (int roomIndex = 1; roomIndex < 4; roomIndex++) {
                screeningRepository.save(Screening.builder()
                        .movie(Movie.builder()
                                .title(movie)
                                .build())
                        .time(LocalDateTime.of(2020, Month.JANUARY, getRandomDayOfMonth(random, roomIndex), 20, 0))
                        .room(Room.builder()
                                .name(String.valueOf(roomIndex))
                                .seats(seatArrangement)
                                .build())
                        .build());
            }
        }
    }

    private int getRandomDayOfMonth(Random random, int roomIndex) {
        return random.nextInt(roomIndex * 10) + 1;
    }

    private Set<Seat> getSeats4x5() {
        String[] rows = {"A", "B", "C", "D"};

        Set<Seat> seats = new HashSet<>();

        for (int i = 1; i < 6; i++) {
            for (String row : rows) {
                Seat seat = Seat.builder()
                        .row(row)
                        .number(String.valueOf(i))
                        .available(true)
                        .build();
                if (i == 1) {
                    seat.setEdge(Edge.LEFT);
                } else if (i == 5) {
                    seat.setEdge(Edge.RIGHT);
                } else {
                    seat.setEdge(Edge.NO);
                }
                seats.add(seat);
            }
        }
        return seats;
    }
}



