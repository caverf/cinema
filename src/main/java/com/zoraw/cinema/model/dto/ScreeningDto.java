package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ScreeningDto {

    private MovieDto movie;
    private LocalDateTime time;
    private RoomDto room;
}
