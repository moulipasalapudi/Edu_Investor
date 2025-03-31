package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;
import com.examly.springapp.model.dto.FeedbackDTO;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;

/**
 * Service implementation class for managing Feedback operations.
 * Implements the FeedbackService interface.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService{
    
    @Autowired
    private FeedbackRepo feedbackRepo;


    @Autowired
    private UserRepo userRepo;


    /**
     * Adds a new Feedback.
     *
     * @param feedback the Feedback to add
     * @return the added Feedback
     */
    

    
   
    /**
     * Retrieves all Feedbacks associated with a specific user ID.
     *
     * @param userId the ID of the user
     * @return a list of Feedbacks for the specified user
     */

    @Override
    public List<Feedback> getFeedbackByUserId(Long userId) {

        User user = userRepo.findById(userId).orElse(null);
        if(user == null) return null;
        
        return feedbackRepo.getFeedbacksByUserId(userId);
    }
    
    /**
     * Retrieves all Feedbacks.
     *
     * @return a list of all Feedbacks
     */
    @Override
    public List<Feedback> getAllFeedbacks() {
        //  return feedbackRepo.getAllFeedbacks();
        return feedbackRepo.getAllFeedbacksWithUsers();
    }
    /**
     * Retrieves feedback by its unique identifier.
     *
     * @param id the unique identifier of the feedback
     * @return an Optional containing the feedback if found, or an empty Optional otherwise
     */
    @Override
    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepo.findById(id);
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        // Retrieve the existing feedback by ID. If it doesn't exist, return null.
        Feedback feedback=feedbackRepo.findById(id).orElse(null);
        if(feedback==null){
            return null;
        }
       // Update the existing feedback's date and text with the new values.
       feedback.setDate(updatedFeedback.getDate());
       feedback.setFeedbackText(updatedFeedback.getFeedbackText());
       // Save the updated feedback back to the repository and return it.
       return feedbackRepo.save(feedback);
        
    }
    /**
     * Deletes a Feedback by its ID.
     *
     * @param feedbackId the ID of the Feedback to delete
     * @return true if the Feedback was deleted, false otherwise
     */

    @Override
    public Feedback deleteFeed(Long id) {
        Feedback feedback = feedbackRepo.findById(id).orElse(null);
        if(feedback==null){
            return null;// Consider throwing an exception if Feedback not found
        }
        feedbackRepo.deleteById(id);
        return feedback;
        
    }
        @Override
    public Feedback addFeedback(FeedbackDTO feedbackDTO) throws UserNotFoundException{
        User existingUser=userRepo.findById(feedbackDTO.getUserId()).orElse(null);
        if(existingUser==null){
            throw new UserNotFoundException("User is Not Found");
        }
        Feedback newFeedback = new Feedback();
        newFeedback.setFeedbackText(feedbackDTO.getFeedbackText());
        newFeedback.setDate(LocalDate.now());
        newFeedback.setUser(existingUser);
        feedbackRepo.save(newFeedback);
        return newFeedback;
           
    }



}
