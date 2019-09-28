package com.zoraw.cinema.model;

import lombok.Data;

import java.util.Set;

@Data
public class Reservation {

    private String firstName;
    private String lastName;
    private Screening screening;
    private Set<Seat> seats;
}
