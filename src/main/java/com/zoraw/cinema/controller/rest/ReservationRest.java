package com.zoraw.cinema.controller.rest;

import com.zoraw.cinema.model.dto.ReservationDto;
import com.zoraw.cinema.model.dto.ReservationResponseDto;
import com.zoraw.cinema.model.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/reservations")
@RestController
public class ReservationRest {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestParam ReservationDto reservationDto) {
        //todo: validate reservationDto

        return ResponseEntity.ok(reservationService.create(reservationDto));
    }

}
