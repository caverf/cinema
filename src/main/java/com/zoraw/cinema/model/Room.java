package com.zoraw.cinema.model;

import lombok.Data;

import java.util.Set;

@Data
public class Room {

    String name;
    Set<Seat> seats;

}
