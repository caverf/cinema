package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class SeatDto {

    private String row;
    private String number;

    @EqualsAndHashCode.Exclude
    private boolean isEdge;

}
