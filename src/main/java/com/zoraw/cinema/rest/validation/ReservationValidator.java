package com.zoraw.cinema.rest.validation;

import com.zoraw.cinema.model.domain.Reservation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ReservationValidator implements ConstraintValidator<ValidReservation, Reservation> {

    @Override
    public boolean isValid(Reservation reservation, ConstraintValidatorContext context) {
        return reservation.getSeats().size() == getNumberOfAllTickets(reservation);
    }

    private int getNumberOfAllTickets(Reservation reservation) {
        return reservation.getTickets()
                .values()
                .stream()
                .mapToInt(value -> value)
                .sum();
    }
}
