package com.ticketing.bookingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // handle custom api exceptions
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException ex) {

        log.error("API error: {} - status: {}", ex.getMessage(), ex.getStatus());

        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());

    }

    // handle resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {

        log.error("Resource not found: {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {

        log.error("Internal error occurred: ", ex);

        return new ResponseEntity<>("an unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
