package com.zoraw.cinema.model.db.mongo.dao;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document
public class Screening {

    private Movie movie;
    private LocalDateTime time;
    private Room room;

}
