package com.zoraw.cinema.model.service.impl;

import com.zoraw.cinema.model.db.mongo.ScreeningRepository;
import com.zoraw.cinema.model.db.mongo.mapper.ScreeningMapper;
import com.zoraw.cinema.model.dto.Screening;
import com.zoraw.cinema.model.dto.ScreeningBasic;
import com.zoraw.cinema.model.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class ScreeningServiceImpl implements ScreeningService {

    private static final Comparator<ScreeningBasic> SCREENING_COMPARATOR = Comparator.comparing(
                    (ScreeningBasic screeningBasic) -> screeningBasic.getMovie().getTitle())
                    .thenComparing(ScreeningBasic::getTime);

    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;


    @Override
    public List<ScreeningBasic> getScreenings(LocalDateTime from, LocalDateTime to) {
        return screeningRepository.findByTimeBetween(from, to)
                .stream()
                .sorted(SCREENING_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public Screening getScreening(String screeningId) {
        return screeningRepository.findById(screeningId)
                .map(screeningMapper::toScreeningDto)
                .orElse(null);
    }

}
