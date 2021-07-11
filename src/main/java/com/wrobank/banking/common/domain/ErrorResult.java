package com.wrobank.banking.common.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorResult {
    private final String message;
    private final HttpStatus status;
}
