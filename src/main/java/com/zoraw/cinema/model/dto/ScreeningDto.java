package com.zoraw.cinema.model.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ScreeningDto {

    private MovieDto movie;
    private LocalDateTime time;
    private RoomDto room;
}
