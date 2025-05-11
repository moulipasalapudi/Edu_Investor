package com.examly.springapp.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.examly.springapp.model.dto.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private  ResponseEntity<ErrorResponseDTO> buildErrorResponse(Exception e, HttpStatus status,String path,String errorCode) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
            LocalDateTime.now(),
            e.getMessage(),
            errorCode,
            path,
            status.value()
        );
        return new ResponseEntity<>(errorResponse, status);
    }
    @ExceptionHandler(LoanAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO>handleLoanAlreadyExistsException(Exception e,HttpServletRequest request){
        return buildErrorResponse(e, HttpStatus.CONFLICT, "LOAN_ALREADY_EXISTS", request.getRequestURI());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String>userNotFoundException(Exception e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @ExceptionHandler(LoanApplicationNotFoundException.class)
    public ResponseEntity<String>LoanApplicationNotFoundException(Exception e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

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