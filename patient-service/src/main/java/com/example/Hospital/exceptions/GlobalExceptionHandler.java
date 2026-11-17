package com.example.Hospital.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice 
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    // protected ProblemDetail handleExceptionInternal(Exception ex, ProblemDetail body, HttpStatus status) {
    //     return ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    // }

    @ExceptionHandler (ResourceNotFoundException.class)
    public ProblemDetail resourceNotFound(ResourceNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler (InvalidInputException.class)
    public ProblemDetail invalidInput(InvalidInputException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ProblemDetail insufficientFunds(InsufficientFundsException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
    }

    @Override 
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request ){
         List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error ->
                    error.getField() + ": " + error.getDefaultMessage())
            .toList();

    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST,
            "Request validation failed"
    );

    problemDetail.setProperty("errors", errors);

    return handleExceptionInternal(
            ex,
            problemDetail,
            headers,
            HttpStatus.BAD_REQUEST,
            request
    );
    }


}
