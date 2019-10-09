package com.zoraw.cinema.model.db.mongo.dao;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MovieDao {

    private String title;
    private String description;
}
