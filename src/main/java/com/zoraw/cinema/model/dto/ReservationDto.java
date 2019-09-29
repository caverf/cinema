package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ReservationDto {

    private String firstName;
    private String lastName;
    private String screeningId;
    private Set<SeatDto> seats;

}
