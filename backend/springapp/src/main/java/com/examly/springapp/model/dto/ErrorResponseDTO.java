package com.examly.springapp.model.dto;

import java.time.LocalDateTime;

public class ErrorResponseDTO {
    private LocalDateTime timestamp;
    private String message;
    private String errorCode;
    private String path;
    private int status;
    public ErrorResponseDTO() {
    }
    public ErrorResponseDTO(LocalDateTime timestamp, String message, String errorCode, String path, int status) {
        this.timestamp = timestamp;
        this.message = message;
        this.errorCode = errorCode;
        this.path = path;
        this.status = status;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
}
