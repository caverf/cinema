package com.zoraw.cinema.model.db.mongo.dao;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReservationSeatDao {

    @EqualsAndHashCode.Include
    private String row;

    @EqualsAndHashCode.Include
    private String number;

}
