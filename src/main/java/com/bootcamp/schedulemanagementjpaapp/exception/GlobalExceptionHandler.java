package com.bootcamp.schedulemanagementjpaapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<?> handleApiException(final ApiException e) {
        return new ResponseEntity<>(e.getResultMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleAllExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}