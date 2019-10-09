package com.zoraw.cinema.rest.validation;

import com.zoraw.cinema.model.dto.ReservationDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Arrays;

@Service
@Validated
public class NameValidation {

    public void validate(@Valid ReservationDto reservationDto) {
        validateName(reservationDto.getFirstName());
        validateSurname(reservationDto.getLastName());
    }

    private void validateName(String firstName) {
        if (isStartWithCapitalAndFollowLowercase(firstName)) {
            throw new ValidationException(firstName);
        }
    }

    private void validateSurname(String lastName) {
        if (isAllPartsOfLastNameAreValid(lastName)) {
            throw new ValidationException(lastName);
        }
    }

    private boolean isAllPartsOfLastNameAreValid(String lastName) {
        String[] surnameParts = lastName.split("-");
        return Arrays.stream(surnameParts).allMatch(this::isStartWithCapitalAndFollowLowercase);
    }


    private boolean isStartWithCapitalAndFollowLowercase(String name) {
        return Character.isUpperCase(name.charAt(0))
                && name.trim()
                .substring(1)
                .chars()
                .allMatch(Character::isLowerCase);
    }


}
