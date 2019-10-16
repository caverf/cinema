package com.zoraw.cinema.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ReservationFailureResponse extends ReservationResponse {

    private static final String TOO_LATE_DESCRIPTION = "TOO_LATE_DESCRIPTION";
    private static final String BAD_SEAT_CONFIGURATION_DESCRIPTION = "BAD_SEAT_CONFIGURATION_DESCRIPTION";

    private String failureDescription;

    public static ReservationFailureResponse createTooLateResponse() {
        return ReservationFailureResponse.builder()
                .failureDescription(TOO_LATE_DESCRIPTION)
                .build();
    }

    public static ReservationFailureResponse createRowChangedByOtherUserResponse(Screening screening) {
        return ReservationFailureResponse.builder()
                .screening(screening)
                .failureDescription(BAD_SEAT_CONFIGURATION_DESCRIPTION)
                .build();
    }
}
