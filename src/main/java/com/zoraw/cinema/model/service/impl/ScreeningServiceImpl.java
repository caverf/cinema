package com.zoraw.cinema.model.service.impl;

import com.zoraw.cinema.model.db.mongo.ScreeningRepository;
import com.zoraw.cinema.model.db.mongo.mapper.ScreeningMapper;
import com.zoraw.cinema.model.dto.ScreeningBasicDto;
import com.zoraw.cinema.model.dto.ScreeningDto;
import com.zoraw.cinema.model.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@RequiredArgsConstructor
@Service
class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;

    @Override
    public Set<ScreeningBasicDto> getScreenings(LocalDateTime from, LocalDateTime to) {
        return screeningRepository.findByTimeBetween(from, to);
    }

    @Override
    public ScreeningDto getScreening(String screeningId) {
        return screeningRepository.findById(screeningId)
                .map(screeningMapper::toScreeningDto)
                .orElse(null);
    }

}
