package com.zoraw.cinema.rest.validation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResult {

    private String message;
    private String fieldName;
    private String fieldValue;

}
