package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SeatDto {

    @EqualsAndHashCode.Include
    private String row;

    @EqualsAndHashCode.Include
    private int number;

    private boolean isAvailable;
    private boolean isEdge;

}
