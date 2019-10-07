package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.dto.ReservationDto;

import java.math.BigDecimal;

public interface TicketCalculation {

    BigDecimal calculateTotalAmount(ReservationDto reservation);
}
