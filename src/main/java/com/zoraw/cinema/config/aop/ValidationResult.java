package com.zoraw.cinema.config.aop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class ValidationResult {

    private String fieldName;
    private String fieldValue;
    private String fieldMessage;

}
