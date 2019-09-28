package com.zoraw.cinema.model.db.mongo.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Seat {

    private String row;
    private String number;
    private boolean isEdge;

}
