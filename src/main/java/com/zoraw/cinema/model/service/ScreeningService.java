package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.dto.ScreeningDto;

import java.time.LocalDateTime;
import java.util.Set;

public interface ScreeningService {

    Set<ScreeningDto> getScreenings(LocalDateTime from, LocalDateTime to);
}
