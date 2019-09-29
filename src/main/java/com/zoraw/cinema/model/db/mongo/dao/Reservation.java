package com.zoraw.cinema.model.db.mongo.dao;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Builder
@Document
public class Reservation {

    private String id;
    private String firstName;
    private String lastName;
    private String screeningId;
    private Set<Seat> seats;
}
