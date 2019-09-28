package com.zoraw.cinema.controller.rest;

import com.zoraw.cinema.controller.validation.DateOrderValidation;
import com.zoraw.cinema.model.dto.ScreeningBasicDto;
import com.zoraw.cinema.model.dto.ScreeningDto;
import com.zoraw.cinema.model.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RequiredArgsConstructor
@RequestMapping(ScreeningRest.API_GATEWAY)
@RestController
public class ScreeningRest {

    static final String API_GATEWAY = "/screenings";

    private final DateOrderValidation dateOrderValidation;
    private final ScreeningService screeningService;

    @GetMapping()
    public ResponseEntity<Set<ScreeningBasicDto>> getScreenings(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {

        dateOrderValidation.isValid(from, to);
        return ResponseEntity.ok(screeningService.getScreenings(from, to));
    }

    @GetMapping("/{screeningId}")
    public ResponseEntity<ScreeningDto> getScreening(@PathVariable String screeningId) {
        return ResponseEntity.ok(screeningService.getScreening(screeningId));
    }
}
