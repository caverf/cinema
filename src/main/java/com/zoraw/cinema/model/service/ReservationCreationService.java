package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.domain.Reservation;

public interface ReservationCreationService {

    boolean create(Reservation reservation);
}
