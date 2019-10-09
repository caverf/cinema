package com.zoraw.cinema.model.db.mongo.dao;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document
public class ScreeningDao {

    private String id;
    private MovieDao movie;
    private LocalDateTime time;
    private RoomDao room;

    @Version
    private Long version;

}
