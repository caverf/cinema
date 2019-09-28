package com.zoraw.cinema.model.dto;

import lombok.Builder;

@Builder
public class SeatDto {

    private String row;
    private String number;
    private boolean isEdge;

}
