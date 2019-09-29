package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.dto.ReservationDto;
import org.springframework.transaction.annotation.Transactional;

public interface ReservationCreationService {

    @Transactional
    boolean create(ReservationDto reservationDto);
}
