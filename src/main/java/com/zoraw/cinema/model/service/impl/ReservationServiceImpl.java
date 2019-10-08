package com.zoraw.cinema.model.service.impl;

import com.zoraw.cinema.model.db.mongo.ScreeningRepository;
import com.zoraw.cinema.model.db.mongo.mapper.ScreeningMapper;
import com.zoraw.cinema.model.dto.ReservationDto;
import com.zoraw.cinema.model.dto.ReservationResponseDto;
import com.zoraw.cinema.model.dto.ScreeningDto;
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
    public ReservationResponseDto create(ReservationDto reservationDto) {
        String screeningId = reservationDto.getScreeningId();
        ScreeningDto screeningDto = getScreeningDto(screeningId);

        if (isReservationTooLate(screeningDto)) {
            return ReservationResponseDto.createTooLateResponse();
        }

        boolean saved = reservationCreationService.create(reservationDto);

        if (saved) {
            return ReservationResponseDto.builder()
                    .isSaved(true)
                    .amount(ticketCalculation.calculateTotalAmount(reservationDto))
                    .expirationTime(screeningDto.getTime().minusMinutes(15))
                    .build();
        }

        return ReservationResponseDto.createRowChangedResponse(getScreeningDto(screeningId));
    }

    private boolean isReservationTooLate(ScreeningDto screeningDto) {
        return LocalDateTime.now()
                .isAfter(screeningDto.getTime().minusMinutes(15));
    }

    private ScreeningDto getScreeningDto(String screeningId) {
        return screeningMapper.toScreeningDto(
                screeningRepository.findById(screeningId)
                        .orElseThrow(BusinessException::new));
    }


}
