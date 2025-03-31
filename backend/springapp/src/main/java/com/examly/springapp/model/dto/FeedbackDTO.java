package com.examly.springapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FeedbackDTO {
   
    @NotBlank(message = "Message is required")
    @Size(max = 500, message = "Message must be less than 500 characters")
    private String feedbackText;
 
 
   @NotNull(message = "Date is required")
    private String date;
 
    private long userId;
 
    public FeedbackDTO() {
    }
 
 
   
 
    public FeedbackDTO(String feedbackText, String date, long userId) {
        this.feedbackText = feedbackText;
        this.date = date;
        this.userId = userId;
    }
 
 
 
 
    public String getFeedbackText() {
        return feedbackText;
    }
 
    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
 
   
 
}