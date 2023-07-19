package com.booking.application.utils;

import com.booking.data.exceptions.BookingMgtException;
import lombok.extern.slf4j.Slf4j;
import com.booking.data.model.APIError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

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

    @ExceptionHandler
    public ResponseEntity<APIError> handleExceptions(Exception e) {
        log.error(e.getLocalizedMessage());
        e.printStackTrace();
        return ResponseEntity.badRequest().body(APIError.builder()
                .message(e.getLocalizedMessage())
                .ok(false)
                .status(HttpStatus.BAD_REQUEST)
                .build());
    }



}

