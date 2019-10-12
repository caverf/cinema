package com.zoraw.cinema.model.db.mongo.dao;

import com.zoraw.cinema.model.domain.Edge;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SeatDao {

    @EqualsAndHashCode.Include
    private String row;

    @EqualsAndHashCode.Include
    private String number;

    private boolean available;
    private Edge edge;
}
