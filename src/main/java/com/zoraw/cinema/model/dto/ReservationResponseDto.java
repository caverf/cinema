package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ReservationResponseDto {

    private boolean isSaved;
    private ScreeningDto screeningDto;

    private BigDecimal amount;
    private LocalDateTime expirationTime;
}
