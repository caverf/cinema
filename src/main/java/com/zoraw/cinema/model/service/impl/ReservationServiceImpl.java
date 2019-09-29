package com.zoraw.cinema.model.service.impl;

import com.zoraw.cinema.model.dto.ReservationDto;
import com.zoraw.cinema.model.dto.ReservationResponseDto;
import com.zoraw.cinema.model.service.ReservationCreationService;
import com.zoraw.cinema.model.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ReservationServiceImpl implements ReservationService {

    private final ReservationCreationService reservationCreationService;

    @Override
    public ReservationResponseDto create(ReservationDto reservationDto) {
        // boolean saved = reservationCreationService.create(reservationDto);

        return null;

    }


}
