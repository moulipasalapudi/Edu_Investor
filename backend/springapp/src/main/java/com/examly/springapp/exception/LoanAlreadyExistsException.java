package com.examly.springapp.exception;

public class LoanAlreadyExistsException extends RuntimeException{
    public LoanAlreadyExistsException(String message){
        super(message);
    }

}
