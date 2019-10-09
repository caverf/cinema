package com.zoraw.cinema.rest.validation;

import com.zoraw.cinema.model.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@Validated
public class DateOrderValidation {

    public void isValid(LocalDateTime from, LocalDateTime to) {
        if (from.isAfter(to)) {
            throw new BusinessException();
        }
    }

}
