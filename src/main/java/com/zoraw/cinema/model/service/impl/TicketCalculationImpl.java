package com.zoraw.cinema.model.service.impl;

import com.zoraw.cinema.model.domain.TicketType;
import com.zoraw.cinema.model.service.TicketCalculation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

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
    public BigDecimal calculateTotalAmount(Map<TicketType, Integer> tickets) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Map.Entry<TicketType, Integer> entry : tickets.entrySet()) {
            sum = sum.add(prices.get(entry.getKey())
                    .multiply(getNumberOfTicket(entry)));
        }

        return sum;
    }

    private BigDecimal getNumberOfTicket(Map.Entry<TicketType, Integer> entry) {
        return BigDecimal.valueOf(entry.getValue());
    }
}
