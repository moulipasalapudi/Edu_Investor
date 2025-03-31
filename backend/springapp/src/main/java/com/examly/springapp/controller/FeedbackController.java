package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.dto.FeedbackDTO;
import com.examly.springapp.service.FeedbackService;

import jakarta.validation.Valid;

@RestController  // Indicates that this class is a RESTful web service controller
public class FeedbackController {

    @Autowired  // Automatically injects the FeedbackService dependency
    private FeedbackService feedbackService;

    @PostMapping("/api/feedback")  // Maps HTTP POST requests to /api/feedback to this method
    public ResponseEntity<Feedback> addFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) throws UserNotFoundException {
        Feedback feedbackObj = feedbackService.addFeedback(feedbackDTO);
        if (feedbackObj != null) {
            return ResponseEntity.status(201).body(feedbackObj);  // Returns HTTP status 201 (Created) with the feedback object
        }
        return ResponseEntity.status(500).body(null);  // Returns HTTP status 500 (Internal Server Error) with no content
    }

    @GetMapping("/api/feedback")  // Maps HTTP GET requests to /api/feedback to this method
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedback = feedbackService.getAllFeedbacks();
        if (!feedback.isEmpty()) {
            return ResponseEntity.status(200).body(feedback);  // Returns HTTP status 200 (OK) with the list of feedbacks
        }
        return ResponseEntity.status(500).body(null);  // Returns HTTP status 500 (Internal Server Error) with no content
    }

    @GetMapping("/api/feedback/user/{userId}")  // Maps HTTP GET requests to /api/feedback/user/{userId} to this method
    public ResponseEntity<List<Feedback>> getFeedbackByUserId(@PathVariable Long userId) {
        List<Feedback> feedbackObj = feedbackService.getFeedbackByUserId(userId);
        if (feedbackObj != null) {
            return ResponseEntity.status(200).body(feedbackObj);  // Returns HTTP status 200 (OK) with the list of feedbacks for the user
        }
        return ResponseEntity.status(500).body(null);  // Returns HTTP status 500 (Internal Server Error) with no content
    }

    @GetMapping("/api/feedback/{feedbackId}")  // Maps HTTP GET requests to /api/feedback/{feedbackId} to this method
    public ResponseEntity<Optional<Feedback>> getFeedbackById(@PathVariable Long feedbackId) {
        Optional<Feedback> feedback = feedbackService.getFeedbackById(feedbackId);
        if (feedback != null) {
            return ResponseEntity.status(200).body(feedback);  // Returns HTTP status 200 (OK) with the feedback object
        }
        return ResponseEntity.status(500).body(null);  // Returns HTTP status 500 (Internal Server Error) with no content
    }

    @PutMapping("/api/feedback/{feedbackId}")  // Maps HTTP PUT requests to /api/feedback/{feedbackId} to this method
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long feedbackId,@Valid  @RequestBody Feedback feedback) {
        Feedback feedbackObj = feedbackService.updateFeedback(feedbackId, feedback);
        if (feedbackObj != null) {
            return ResponseEntity.status(200).body(feedbackObj);  // Returns HTTP status 200 (OK) with the updated feedback object
        }
        return ResponseEntity.status(500).body(null);  // Returns HTTP status 500 (Internal Server Error) with no content
    }

    @DeleteMapping("/api/feedback/{feedbackId}")  // Maps HTTP DELETE requests to /api/feedback/{feedbackId} to this method
    public ResponseEntity<Feedback> deleteFeed(@PathVariable Long feedbackId) {
        Feedback feedback = feedbackService.deleteFeed(feedbackId);
        if (feedback != null) {
            return ResponseEntity.status(200).body(feedback);  // Returns HTTP status 200 (OK) with the deleted feedback object
        }
        return ResponseEntity.status(500).body(null);  // Returns HTTP status 500 (Internal Server Error) with no content
    }
}
