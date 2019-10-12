package com.zoraw.cinema.model.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ReservationResponse {

    private static final String TOO_LATE_DESCRIPTION = "TOO_LATE_DESCRIPTION";
    private static final String ANOTHER_USER_CHANGED_THE_SAME_ROW_DESCRIPTION = "ANOTHER_USER_CHANGED_THE_SAME_ROW_DESCRIPTION";

    private boolean isSaved;
    private String failureDescription;
    private Screening screening;

    private BigDecimal amount;
    private LocalDateTime expirationTime;

    public static ReservationResponse createTooLateResponse() {
        return ReservationResponse.builder()
                .isSaved(false)
                .failureDescription(TOO_LATE_DESCRIPTION)
                .build();
    }

    public static ReservationResponse createRowChangedResponse(Screening screening) {
        return ReservationResponse.builder()
                .isSaved(false)
                .screening(screening)
                .failureDescription(ANOTHER_USER_CHANGED_THE_SAME_ROW_DESCRIPTION)
                .build();
    }
}
