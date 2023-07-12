package com.herotech.application;

import exceptions.BookingMgtException;
import lombok.extern.slf4j.Slf4j;
import model.APIError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<APIError> handleExceptions(BookingMgtException e) {
        log.error(e.getLocalizedMessage());
        return ResponseEntity.badRequest().body(APIError.builder()
                .message(e.getLocalizedMessage())
                .ok(false)
                .status(HttpStatus.BAD_REQUEST)
                .build());
    }
}

