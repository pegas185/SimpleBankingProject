package com.wrobank.banking.infrastructure;

import com.wrobank.banking.common.domain.CustomerWasNotFoundException;
import com.wrobank.banking.common.domain.ErrorResult;
import com.wrobank.banking.common.domain.UserWasNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class BankingDefaultErrorHandler {

    @ExceptionHandler(CustomerWasNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult handleCustomException(CustomerWasNotFoundException ex) {
        return produceNotFoundResult(ex);
    }

    @ExceptionHandler(UserWasNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult handleCustomException(UserWasNotFoundException ex) {
        return produceNotFoundResult(ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleCustomException(ConstraintViolationException ex) {
        return produceBadRequestResult(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleCustomException(MethodArgumentNotValidException ex) {
        return produceBadRequestResult(ex);
    }

    private ErrorResult produceNotFoundResult(Exception ex) {
        return produceErrorResult(HttpStatus.NOT_FOUND, ex);
    }

    private ErrorResult produceBadRequestResult(Exception ex) {
        return produceErrorResult(HttpStatus.BAD_REQUEST, ex);
    }

    private ErrorResult produceErrorResult(HttpStatus status, Exception ex) {
        return ErrorResult.builder()
                          .message(ex.getLocalizedMessage())
                          .status(status)
                          .build();
    }

}
