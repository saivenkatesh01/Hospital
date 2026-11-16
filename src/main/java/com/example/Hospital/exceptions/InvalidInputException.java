package com.example.Hospital.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }

}
