package com.zoraw.cinema.model.dto;

import com.zoraw.cinema.rest.validation.LastName;
import com.zoraw.cinema.rest.validation.Name;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
public class ReservationDto {

    @Size(min = 3, max = 50)
    @NotBlank
    @Name
    private String firstName;

    @Size(min = 3, max = 50)
    @NotBlank
    @LastName
    private String lastName;

    @NotNull
    private String screeningId;

    @Size(min = 1)
    private Set<SeatDto> seats;
}
