package com.ftn.studentservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e){

        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ExceptionMessage exceptionMessage = new ExceptionMessage(
                e.getMessage(),
                notFound,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionMessage, notFound);
    }
}