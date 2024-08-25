package com.bootcamp.schedulemanagementjpaapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<?> handleApiException(final ApiException e) {
        return new ResponseEntity<>(e.getResultMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleAllExceptions(final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        e.getFieldErrors().forEach(error -> sb.append(error.getDefaultMessage()).append("\n"));
        return new ResponseEntity<>(sb.deleteCharAt(sb.length() - 1).toString(), HttpStatus.BAD_REQUEST);
    }
}