package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.dto.Reservation;

import java.math.BigDecimal;

public interface TicketCalculation {

    BigDecimal calculateTotalAmount(Reservation reservation);
}
