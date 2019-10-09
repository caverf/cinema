package com.zoraw.cinema.model.db.mongo.dao;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RoomDao {

    private String name;
    private Set<SeatDao> seats;

}
