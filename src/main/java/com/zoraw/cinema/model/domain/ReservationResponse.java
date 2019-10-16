package com.zoraw.cinema.model.domain;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ReservationResponse {

    protected Screening screening;
    private BigDecimal amount;
    private LocalDateTime expirationTime;

}
