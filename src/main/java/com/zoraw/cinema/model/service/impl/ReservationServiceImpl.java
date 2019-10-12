package com.zoraw.cinema.model.service.impl;

import com.zoraw.cinema.model.db.mongo.ScreeningRepository;
import com.zoraw.cinema.model.db.mongo.mapper.ScreeningMapper;
import com.zoraw.cinema.model.domain.Reservation;
import com.zoraw.cinema.model.domain.ReservationResponse;
import com.zoraw.cinema.model.domain.Screening;
import com.zoraw.cinema.model.exception.BusinessException;
import com.zoraw.cinema.model.service.ReservationCreationService;
import com.zoraw.cinema.model.service.ReservationService;
import com.zoraw.cinema.model.service.TicketCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
class ReservationServiceImpl implements ReservationService {

    private final ReservationCreationService reservationCreationService;
    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;
    private final TicketCalculation ticketCalculation;

    @Override
    public ReservationResponse create(Reservation reservation) {
        String screeningId = reservation.getScreeningId();
        Screening screening = getScreeningDto(screeningId);

        if (isReservationTooLate(screening)) {
            return ReservationResponse.createTooLateResponse();
        }

        boolean saved = reservationCreationService.create(reservation);

        if (saved) {
            return ReservationResponse.builder()
                    .isSaved(true)
                    .amount(ticketCalculation.calculateTotalAmount(reservation))
                    .expirationTime(screening.getTime().minusMinutes(15))
                    .build();
        }

        return ReservationResponse.createRowChangedResponse(getScreeningDto(screeningId));
    }

    private boolean isReservationTooLate(Screening screening) {
        return LocalDateTime.now()
                .isAfter(screening.getTime().minusMinutes(15));
    }

    private Screening getScreeningDto(String screeningId) {
        return screeningMapper.toScreeningDto(
                screeningRepository.findById(screeningId)
                        .orElseThrow(BusinessException::new));
    }


}
