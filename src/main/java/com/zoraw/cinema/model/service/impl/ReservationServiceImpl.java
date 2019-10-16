package com.zoraw.cinema.model.service.impl;

import com.zoraw.cinema.model.db.mongo.ScreeningRepository;
import com.zoraw.cinema.model.db.mongo.mapper.ScreeningMapper;
import com.zoraw.cinema.model.domain.Reservation;
import com.zoraw.cinema.model.domain.ReservationFailureResponse;
import com.zoraw.cinema.model.domain.ReservationResponse;
import com.zoraw.cinema.model.domain.Screening;
import com.zoraw.cinema.model.exception.ScreeningNotFoundException;
import com.zoraw.cinema.model.service.ReservationCreationService;
import com.zoraw.cinema.model.service.ReservationService;
import com.zoraw.cinema.model.service.TicketPriceCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
class ReservationServiceImpl implements ReservationService {

    @Value("${reservation.minimumMinutesBeforeScreening}")
    private int minimumMinutesBeforeScreening;

    private final ReservationCreationService reservationCreationService;
    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;
    private final TicketPriceCalculationService ticketPriceCalculationService;

    @Override
    public ReservationResponse create(Reservation reservation) {
        String screeningId = reservation.getScreeningId();
        Screening screening = getScreening(screeningId);

        if (isReservationTooLate(screening)) {
            return ReservationFailureResponse.createTooLateResponse();
        }

        boolean saved = reservationCreationService.create(reservation);

        if (saved) {
            return ReservationResponse.builder()
                    .amount(ticketPriceCalculationService.calculateTotalAmount(reservation.getTickets()))
                    .expirationTime(screening.getTime().minusMinutes(minimumMinutesBeforeScreening))
                    .build();
        }

        return ReservationFailureResponse.createBadSeatConfigurationResponse(getScreening(screeningId));
    }

    private boolean isReservationTooLate(Screening screening) {
        return LocalDateTime.now()
                .isAfter(screening.getTime().minusMinutes(minimumMinutesBeforeScreening));
    }

    private Screening getScreening(String screeningId) {
        return screeningMapper.toScreening(
                screeningRepository.findById(screeningId)
                        .orElseThrow(() -> new ScreeningNotFoundException(screeningId)));
    }


}
