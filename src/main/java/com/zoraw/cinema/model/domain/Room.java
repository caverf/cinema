package com.zoraw.cinema.model.domain;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Data
@Builder
public class Room {

    private String name;
    private Set<Seat> seats;

    public boolean canReserveSeats(Set<Seat> seatsToReserve) {
        if (isSeatsToReserveAreAvailable(seatsToReserve)) {
            return false;
        }

        return isAnySeatLeftOver(seatsToReserve);
    }

    private boolean isSeatsToReserveAreAvailable(Set<Seat> seatsToReserve) {
        return seats.stream()
                .anyMatch(roomSeat -> seatsToReserve.contains(roomSeat) && !roomSeat.isAvailable());
    }


    private boolean isAnySeatLeftOver(Set<Seat> seatsToReserve) {
        List<Seat> seatsAfterReservation = getSeatsAfterReservation(seatsToReserve);

        return seatsAfterReservation.stream()
                .filter(Seat::isAvailable)
                .filter(seat -> !seat.isEdge())
                .allMatch(seat -> isAnyNeighbourAvailable(seatsAfterReservation, seat));
    }

    private List<Seat> getSeatsAfterReservation(Set<Seat> seatsToReserve) {
        List<Seat> seatsAfterReservation = new ArrayList<>(seats);

        for (Seat seat : seatsAfterReservation) {
            if (seatsToReserve.contains(seat)) {
                seat.setAvailable(false);
            }
        }
        return seatsAfterReservation;
    }

    private boolean isAnyNeighbourAvailable(List<Seat> seatsInReservedRows, Seat seat) {
        return isNeighbourAvailable(seatsInReservedRows, seat, 1)
                || isNeighbourAvailable(seatsInReservedRows, seat, -1);
    }

    private boolean isNeighbourAvailable(List<Seat> seatsInReservedRows, Seat seat, int distance) {
        return Optional.of(seatsInReservedRows.get(seatsInReservedRows.indexOf(Seat.builder()
                .row(seat.getRow())
                .number(seat.getNumber() + distance)
                .build())))
                .map(Seat::isAvailable)
                .orElse(false);
    }

}
