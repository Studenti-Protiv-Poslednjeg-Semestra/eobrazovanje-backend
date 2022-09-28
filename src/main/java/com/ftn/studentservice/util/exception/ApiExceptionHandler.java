package com.ftn.studentservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toCollection(ArrayList::new));
        String errorMessage = errorMessages.stream().collect(Collectors.joining(";"));

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

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

    @ExceptionHandler(value = {NotEnoughFunds.class})
    public ResponseEntity<Object> handleNotEnoughFoundsException(NotEnoughFunds e){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ExceptionMessage exceptionMessage = new ExceptionMessage(
                e.getMessage(),
                badRequest,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionMessage, badRequest);
    }

    @ExceptionHandler(value = {SubjectCreationException.class})
    public ResponseEntity<Object> handleSubjectCreationException(SubjectCreationException e){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ExceptionMessage exceptionMessage = new ExceptionMessage(
                e.getMessage(),
                badRequest,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionMessage, badRequest);
    }

    @ExceptionHandler(value = {InvalidDateException.class})
    public ResponseEntity<Object> handleInvalidDateException(InvalidDateException e){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ExceptionMessage exceptionMessage = new ExceptionMessage(
                e.getMessage(),
                badRequest,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionMessage, badRequest);
    }

    @ExceptionHandler(value = {SyllabusCreationException.class})
    public ResponseEntity<Object> handleSyllabusCreationException(SyllabusCreationException e){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ExceptionMessage exceptionMessage = new ExceptionMessage(
                e.getMessage(),
                badRequest,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionMessage, badRequest);
    }

}
