package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ScreeningBasicDto {

    private String id;
    private MovieDto movie;
    private LocalDateTime time;
}
