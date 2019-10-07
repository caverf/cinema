package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class RoomDto {

    String name;
    Set<SeatDto> seats;

    public boolean canReserveSeats(Set<SeatDto> seatsToReserve) {
        if (seatsToReserve.isEmpty()) {
            return false;
        }

        Set<SeatDto> seatsAfterReservation = new HashSet<>(seats);

        for (SeatDto seat : seatsAfterReservation) {
            if (seatsToReserve.contains(seat)) {
                seat.setAvailable(false);
            }
        }

        Set<String> rowsWithReservedSeats = seatsToReserve.stream().map(SeatDto::getRow).collect(Collectors.toSet());

        List<SeatDto> seatsInReservedRows = seatsAfterReservation
                .stream()
                .filter(seat -> rowsWithReservedSeats.contains(seat.getRow()))
                .sorted()
                .collect(Collectors.toList());


        for (String row : rowsWithReservedSeats) {
            int counter = 0;
            for (SeatDto seat : seatsInReservedRows) {
                if (seat.getRow().equals(row)) {
                    if (!seat.isAvailable()) {
                        if (counter == 1) {
                            return false;
                        }
                    }
                    if(seat.isAvailable()) {
                        counter++;
                    }
                    if(seat.getEdge() == Edge.RIGHT || seat.getEdge() == Edge.LEFT_AND_RIGHT){
                        counter = 0;
                    }
                }
            }
        }
        return true;
    }

}
