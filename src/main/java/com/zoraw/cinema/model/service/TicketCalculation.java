package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.domain.Reservation;
import com.zoraw.cinema.model.domain.TicketType;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Map;

public interface TicketCalculation {

    BigDecimal calculateTotalAmount(@Size(min = 1) Map<TicketType, Integer> tickets);
}
