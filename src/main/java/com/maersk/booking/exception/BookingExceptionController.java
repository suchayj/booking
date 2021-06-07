package com.maersk.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class BookingExceptionController {

    @ExceptionHandler(value = BookingSaveException.class)

    public ResponseEntity<Object> exception(BookingSaveException exception) {
        final String errorMessage = "Sorry there was a problem processing your request";
        log.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
