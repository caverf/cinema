package com.zoraw.cinema.model.db.mongo.dao;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Movie {

    private String title;
    private String description;
}
