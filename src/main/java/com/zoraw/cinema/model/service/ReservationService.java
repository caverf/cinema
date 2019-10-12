package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.domain.Reservation;
import com.zoraw.cinema.model.domain.ReservationResponse;

public interface ReservationService {

    ReservationResponse create(Reservation reservation);

}
