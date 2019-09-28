package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatDto {

    private String row;
    private String number;
    private boolean isEdge;

}
