package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RoomDto {

    String name;
    Set<SeatDto> seats;

    public boolean canReserveSeats(Set<SeatDto> seatsToReserve) {
        return true;
    }
}
