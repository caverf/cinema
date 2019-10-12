package com.zoraw.cinema.model.exception;

public class ScreeningNotFoundException extends BusinessException {

    private static final String SCREENING_NOT_FOUND_DESCRIPTION = "SCREENING_NOT_FOUND_DESCRIPTION";

    private final String screeningId;
    public ScreeningNotFoundException(String screeningId) {
        super(SCREENING_NOT_FOUND_DESCRIPTION);
        this.screeningId = screeningId;
    }
}
