package com.ftn.studentservice.util.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;

}
