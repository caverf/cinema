package com.zoraw.cinema.model.domain;

import lombok.Data;

@Data
public class ReservationSeat {

    private String row;
    private int number;
    private TicketType ticketType;

}
