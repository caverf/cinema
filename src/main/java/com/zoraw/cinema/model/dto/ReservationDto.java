package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
public class ReservationDto {

    @Size(min = 3, max = 50)
    @NotBlank
    private String firstName;

    @Size(min = 3, max = 50)
    @NotBlank
    private String lastName;

    private String screeningId;

    @Size(min = 1)
    private Set<SeatDto> seats;
}
