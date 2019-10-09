package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class Room {

    String name;
    Set<Seat> seats;

    public boolean canReserveSeats(Set<Seat> seatsToReserve) {
        if (seatsToReserve.isEmpty()) {
            return false;
        }

        Set<Seat> seatsAfterReservation = new HashSet<>(seats);

        for (Seat seat : seatsAfterReservation) {
            if (seatsToReserve.contains(seat)) {
                seat.setAvailable(false);
            }
        }

        Set<String> rowsWithReservedSeats = seatsToReserve.stream().map(Seat::getRow).collect(Collectors.toSet());

        List<Seat> seatsInReservedRows = seatsAfterReservation
                .stream()
                .filter(seat -> rowsWithReservedSeats.contains(seat.getRow()))
                .sorted()
                .collect(Collectors.toList());


        for (String row : rowsWithReservedSeats) {
            int counter = 0;
            for (Seat seat : seatsInReservedRows) {
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
