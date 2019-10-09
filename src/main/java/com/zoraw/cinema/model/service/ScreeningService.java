package com.zoraw.cinema.model.service;

import com.zoraw.cinema.model.dto.Screening;
import com.zoraw.cinema.model.dto.ScreeningBasic;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningService {

    List<ScreeningBasic> getScreenings(LocalDateTime from, LocalDateTime to);

    Screening getScreening(String screeningId);
}
