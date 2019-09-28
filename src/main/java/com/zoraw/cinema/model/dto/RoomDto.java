package com.zoraw.cinema.model.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public class RoomDto {

    String name;
    Set<SeatDto> seats;

}
