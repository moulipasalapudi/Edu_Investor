package com.examly.springapp.model;
 
import java.time.LocalDate;
 
import com.fasterxml.jackson.annotation.JsonBackReference;
 
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
 
// Entity class representing a Feedback.
// Annotated with `@Entity` to map the class to a database table.
 
@Entity
public class Feedback {
 
    // Unique identifier for a Feedback.
    // Annotated with `@Id` to denote primary key.
    // Uses `@GeneratedValue` with the strategy `GenerationType.IDENTITY` for automatic ID generation.
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;
 
 
    // @NotBlank(message = "Message is required")
    // @Size(max = 500, message = "Message must be less than 500 characters")
    private String feedbackText;
    private LocalDate date;
 
    /**
     * User associated with the Feedback.
     * Annotated with `@ManyToOne` to denote the many-to-one relationship with the `User` entity.
     */
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    // @JsonBackReference
    private User user;
 
    // Default constructor to initialize the Feedback object.
 
    public Feedback() {
    }
 
    // Parameterized constructor to initialize the Feedback object.
    public Feedback(Long feedbackId, String feedbackText, LocalDate date, User user) {
        this.feedbackId = feedbackId;
        this.feedbackText = feedbackText;
        this.date = date;
        this.user = user;
    }
 
    // Getter and setter methods for feedbackId.
    /**
     * Gets the feedback ID.
     * @return the feedback ID
     */
 
    public Long getFeedbackId() {
        return feedbackId;
    }
   
    /**
     * Sets the feedback ID.
     * @param feedbackId the feedback ID to set
     */
   
    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }
 
    public String getFeedbackText() {
        return feedbackText;
    }
 
    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
 
    public LocalDate getDate() {
        return date;
    }
 
    public void setDate(LocalDate date) {
        this.date = date;
    }
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
   
}
 