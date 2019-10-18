package com.zoraw.cinema.rest;

import com.zoraw.cinema.model.domain.Screening;
import com.zoraw.cinema.model.service.ScreeningService;
import com.zoraw.cinema.model.service.dto.MovieWithScreeningsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/screenings")
@RestController
public class ScreeningRest {

    private final ScreeningService screeningService;

    @GetMapping()
    public ResponseEntity<List<MovieWithScreeningsDto>> getScreenings(
            //local date time? jak to będzie działało dla roznych stref czasowych?
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {

        return ResponseEntity.ok(screeningService.getScreenings(from, to));
    }

    //U gory zwracasz Dto a tu po prostu Screening. Uspojnij konwencje nazewnicze.
    @GetMapping("/{screeningId}")
    public ResponseEntity<Screening> getScreening(@PathVariable String screeningId) {
        return ResponseEntity.ok(screeningService.getScreening(screeningId));
    }

}
