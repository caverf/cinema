package com.zoraw.cinema.rest.validation;

import com.zoraw.cinema.model.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateOrderValidation {

    public void isValid(LocalDateTime from, LocalDateTime to) {

        //todo: add validation for comparing with present date
        if (from.isAfter(to)) {
            throw new BusinessException();
        }
    }

}
