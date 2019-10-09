package com.zoraw.cinema.model.service.impl;

import com.zoraw.cinema.model.db.mongo.ScreeningRepository;
import com.zoraw.cinema.model.db.mongo.mapper.ScreeningMapper;
import com.zoraw.cinema.model.dto.Movie;
import com.zoraw.cinema.model.dto.Screening;
import com.zoraw.cinema.model.dto.ScreeningBasic;
import com.zoraw.cinema.model.service.ScreeningService;
import com.zoraw.cinema.model.service.dto.MovieWithScreeningsDto;
import com.zoraw.cinema.model.service.dto.ScreeningDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
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
    public List<MovieWithScreeningsDto> getScreenings(LocalDateTime from, LocalDateTime to) {

        Set<Screening> screenings = screeningRepository.findByTimeBetween(from, to);

        return screenings.stream()
                .map(Screening::getMovie)
                .distinct()
                .map(movie -> createMovieWithScreenings(screenings, movie))
                .sorted(Comparator.comparing(movieWithScreening -> movieWithScreening.getMovie().getTitle()))
                .collect(Collectors.toList());
    }

    private MovieWithScreeningsDto createMovieWithScreenings(Set<Screening> screenings, Movie movie) {
        return MovieWithScreeningsDto.builder()
                .movie(movie)
                .screenings(screenings.stream()
                        .filter(screening -> screening.getMovie().equals(movie))
                        .map(screening -> new ScreeningDto(screening.getId(), screening.getTime()))
                        .sorted(Comparator.comparing(ScreeningDto::getTime))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Screening getScreening(String screeningId) {
        return screeningRepository.findById(screeningId)
                .map(screeningMapper::toScreeningDto)
                .orElse(null);
    }
}
