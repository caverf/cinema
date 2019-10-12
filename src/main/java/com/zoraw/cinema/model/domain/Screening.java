package com.zoraw.cinema.model.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Screening {

    private String id;
    private Movie movie;
    private LocalDateTime time;
    private Room room;
}
