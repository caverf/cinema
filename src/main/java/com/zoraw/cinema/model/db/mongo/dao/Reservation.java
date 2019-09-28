package com.zoraw.cinema.model.db.mongo.dao;

import lombok.Builder;

import java.util.Set;

@Builder
public class Reservation {

    private String firstName;
    private String lastName;
    private Screening screening;
    private Set<Seat> seats;
}
