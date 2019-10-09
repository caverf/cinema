package com.zoraw.cinema.model.service.impl;

import com.zoraw.cinema.model.dto.Reservation;
import com.zoraw.cinema.model.dto.Seat;
import com.zoraw.cinema.model.dto.TicketType;
import com.zoraw.cinema.model.service.TicketCalculation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.EnumMap;

@Service
public class TicketCalculationImpl implements TicketCalculation {

    @Value("${ticket.adult}")
    private BigDecimal adult;

    @Value("${ticket.student}")
    private BigDecimal student;

    @Value("${ticket.child}")
    private BigDecimal child;

    private EnumMap<TicketType, BigDecimal> prices;

    @PostConstruct
    private void initPriceMap() {
        prices = new EnumMap<>(TicketType.class);
        prices.put(TicketType.ADULT, adult);
        prices.put(TicketType.STUDENT, student);
        prices.put(TicketType.CHILD, child);
    }

    @Override
    public BigDecimal calculateTotalAmount(Reservation reservation) {

        return reservation.getSeats()
                .stream()
                .map(Seat::getTicketType)
                .map(ticketType -> prices.get(ticketType))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
