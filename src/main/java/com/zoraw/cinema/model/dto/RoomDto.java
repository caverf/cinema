package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class RoomDto {

    String name;
    Set<SeatDto> seats;

    public boolean canReserveSeats(Set<SeatDto> seatsToReserve) {
        if(seatsToReserve.isEmpty()) {
            return false;
        }

        Map<SeatDto, Boolean> tmpSeats = seats.stream()
                .collect(Collectors.toMap(seat -> seat, SeatDto::isAvailable));

        for (SeatDto seat : tmpSeats.keySet()) {
            if (seatsToReserve.contains(seat)) {
                tmpSeats.put(seat, false);
            }
        }

        return tmpSeats.entrySet()
                .stream()
                .filter(seat -> seatsToReserve.stream()
                        .anyMatch(reservedSeat -> seat.getKey().getRow().equals(reservedSeat.getRow())))
                .filter(seat -> !seat.getKey().isEdge())
                .filter(seat -> seat.getValue())
                .allMatch(seat -> tmpSeats.get(SeatDto.builder()
                        .row(seat.getKey().getRow())
                        .number(seat.getKey().getNumber() - 1)
                        .build())
                        || tmpSeats.get(SeatDto.builder()
                        .row(seat.getKey().getRow())
                        .number(seat.getKey().getNumber() + 1)
                        .build()));

    }

}
