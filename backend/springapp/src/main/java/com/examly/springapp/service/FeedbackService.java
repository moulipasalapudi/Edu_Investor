package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.dto.FeedbackDTO;

public interface FeedbackService {
    
    Feedback addFeedback(FeedbackDTO feedback) throws UserNotFoundException;

    List<Feedback> getFeedbackByUserId(Long userId);

    List<Feedback> getAllFeedbacks();

    Optional<Feedback> getFeedbackById(Long id);

    Feedback updateFeedback(Long id, Feedback updatedFeedback);

    Feedback deleteFeed(Long id);
}
