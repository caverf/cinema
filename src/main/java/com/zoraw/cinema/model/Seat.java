package com.zoraw.cinema.model;

import lombok.Data;

@Data
public class Seat {

    private String row;
    private String number;
    private boolean isEdge;

}
