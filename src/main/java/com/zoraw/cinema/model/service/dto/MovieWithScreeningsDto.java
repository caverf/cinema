package com.zoraw.cinema.model.service.dto;


import com.zoraw.cinema.model.dto.Movie;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieWithScreeningsDto {
    private Movie movie;
    private List<ScreeningDto> screenings;
}
