package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.dto.ScreeningBasicDto;
import com.zoraw.cinema.model.dto.ScreeningDto;

import java.time.LocalDateTime;
import java.util.Set;

public interface ScreeningService {

    Set<ScreeningBasicDto> getScreenings(LocalDateTime from, LocalDateTime to);

    ScreeningDto getScreening(String screeningId);
}
