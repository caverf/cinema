package com.zoraw.cinema.model.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public class ReservationDto {

    private String firstName;
    private String lastName;
    private ScreeningDto screening;
    private Set<SeatDto> seats;
}
