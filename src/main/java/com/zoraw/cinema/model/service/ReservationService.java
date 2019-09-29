package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.dto.ReservationDto;
import com.zoraw.cinema.model.dto.ReservationResponseDto;

public interface ReservationService {

    ReservationResponseDto create(ReservationDto reservationDto);

}
