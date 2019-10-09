package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.dto.Reservation;
import com.zoraw.cinema.model.dto.ReservationResponse;

public interface ReservationService {

    ReservationResponse create(Reservation reservation);

}
