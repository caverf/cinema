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

@RequiredArgsConstructor
@Service
class ReservationServiceImpl implements ReservationService {

    private final ReservationCreationService reservationCreationService;
    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;
    private final TicketCalculation ticketCalculation;


    @Override
    public ReservationResponseDto create(ReservationDto reservationDto) {
        boolean saved = reservationCreationService.create(reservationDto);

        if (saved) {
            return ReservationResponseDto.builder()
                    .isSaved(true)
                    .amount(ticketCalculation.calculateTotalAmount(reservationDto))
                    //todo: expiration date
                    .build();
        }

        return ReservationResponseDto.builder()
                .isSaved(false)
                .screeningDto(getScreeningDto(reservationDto))
                .build();
    }

    private ScreeningDto getScreeningDto(ReservationDto reservationDto) {
        return screeningMapper.toScreeningDto(
                screeningRepository.findById(reservationDto.getScreeningId())
                        .orElseThrow(BusinessException::new));
    }


}
