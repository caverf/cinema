package com.zoraw.cinema.controller.rest;

import com.zoraw.cinema.model.dto.ScreeningDto;
import com.zoraw.cinema.model.service.ScreeningService;
import com.zoraw.cinema.controller.validation.DateOrderValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class ScreeningRest {

    private final DateOrderValidation dateOrderValidation;
    private final ScreeningService screeningService;

    @GetMapping
    public ResponseEntity<Set<ScreeningDto>> getScreenings(@PathVariable LocalDateTime from, @PathVariable LocalDateTime to) {

        dateOrderValidation.isValid(from, to);
        return ResponseEntity.ok(screeningService.getScreenings(from, to));
    }
}
