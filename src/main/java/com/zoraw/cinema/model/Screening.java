package com.zoraw.cinema.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Screening {

    private Movie movie;
    private LocalDateTime time;
    private Room room;
}
