package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.dto.ReservationDto;

public interface ReservationCreationService {

    boolean create(ReservationDto reservationDto);
}
