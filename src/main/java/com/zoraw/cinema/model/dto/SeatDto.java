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
    private String number;

    private Boolean isAvailable;
    private Boolean isEdge;

}
