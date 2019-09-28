package com.zoraw.cinema.model.db.mongo.dao;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Room {

    String name;
    Set<Seat> seats;

}
