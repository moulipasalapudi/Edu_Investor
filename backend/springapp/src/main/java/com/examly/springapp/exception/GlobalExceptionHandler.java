package com.examly.springapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoanAlreadyExistsException.class)
    public ResponseEntity<String>handleLoanAlreadyExistsException(Exception e){
        return ResponseEntity.status(409).body(e.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String>userNotFoundException(Exception e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @ExceptionHandler(LoanApplicationNotFoundException.class)
    public ResponseEntity<String>LoanApplicationNotFoundException(Exception e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /**
     * Handles PetNotFoundException.
     *
     * @param e The PetNotFoundException thrown.
     * @return A ResponseEntity with HTTP status 400 and the exception message.
     */
     @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}