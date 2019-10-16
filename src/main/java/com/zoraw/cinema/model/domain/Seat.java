package com.zoraw.cinema.model.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Seat implements Comparable<Seat>{

    @EqualsAndHashCode.Include
    private String row;

    @EqualsAndHashCode.Include
    private int number;

    private boolean available;
    private boolean edge;

    @Override
    public int compareTo(Seat seat) {
        return this.number - seat.getNumber();
    }
}
