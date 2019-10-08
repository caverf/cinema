package com.zoraw.cinema.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ReservationResponseDto {

    private static final String TOO_LATE_DESCRIPTION = "TOO_LATE_DESCRIPTION";
    private static final String ANOTHER_USER_CHANGED_THE_SAME_ROW_DESCRIPTION = "ANOTHER_USER_CHANGED_THE_SAME_ROW_DESCRIPTION";

    private boolean isSaved;
    private String failureDescription;
    private ScreeningDto screeningDto;

    private BigDecimal amount;
    private LocalDateTime expirationTime;

    public static ReservationResponseDto createTooLateResponse() {
        return ReservationResponseDto.builder()
                .isSaved(false)
                .failureDescription(TOO_LATE_DESCRIPTION)
                .build();
    }

    public static ReservationResponseDto createRowChangedResponse(ScreeningDto screeningDto) {
        return ReservationResponseDto.builder()
                .isSaved(false)
                .screeningDto(screeningDto)
                .failureDescription(ANOTHER_USER_CHANGED_THE_SAME_ROW_DESCRIPTION)
                .build();
    }
}
