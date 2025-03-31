/**
 * LoanApplicationController class acts as an intermediary between the service logic and the client.
 * It handles all HTTP requests related to loan applications.
 * 
 * @Author [Your Name]
 */
package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.LoanApplicationNotFoundException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.LoanApplication;
import com.examly.springapp.model.dto.LoanApplicationDTO;
import com.examly.springapp.service.LoanApplicationService;

import jakarta.validation.Valid;

@RestController
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    // Constructor to inject the dependencies
    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    /**
     * Handles HTTP POST requests to create a new loan application.
     * 
     * @param loanApplication the loan application details to be created
     * @return the created loan application along with appropriate HTTP status code
     * @throws LoanApplicationNotFoundException 
     * @throws UserNotFoundException 
     */
    @PostMapping("/api/loanapplication")
    public ResponseEntity<LoanApplication> addLoanApplication(@Valid @RequestBody LoanApplicationDTO loanApplication) throws UserNotFoundException, LoanApplicationNotFoundException {
        LoanApplication savedLoanApplication = loanApplicationService.addLoanApplication(loanApplication);
        if (savedLoanApplication != null) {
            return ResponseEntity.status(201).body(savedLoanApplication);
        }
        return ResponseEntity.status(500).body(null);
    }

    /**
     * Handles HTTP GET requests to retrieve a loan application by its ID.
     * 
     * @param id the ID of the loan application to be retrieved
     * @return the loan application with the specified ID along with appropriate HTTP status code
     */
    @GetMapping("/api/loanapplication/{loanapplicationId}")
    public ResponseEntity<Optional<LoanApplication>> getLoanApplicationById(@PathVariable Long loanapplicationId) {
        Optional<LoanApplication> loanApplication = loanApplicationService.getLoanApplicationById(loanapplicationId);
        if (loanApplication != null) {
            return ResponseEntity.status(201).body(loanApplication);
        }
        return ResponseEntity.status(500).body(null);
    }

    /**
     * Handles HTTP GET requests to retrieve loan applications by user ID.
     * 
     * @param userId the ID of the user whose loan applications are to be retrieved
     * @return the list of loan applications for the specified user along with appropriate HTTP status code
     */
    @GetMapping("/api/user/{userId}")
    public ResponseEntity<List<LoanApplication>> getLoanApplicationByUserId(@PathVariable Long userId) {
        List<LoanApplication> loanApplications = loanApplicationService.getLoanApplicationByUserId(userId);
        if (loanApplications != null) {
            return ResponseEntity.status(200).body(loanApplications);
        }
        return ResponseEntity.status(500).body(null);
    }

    /**
     * Handles HTTP GET requests to retrieve all loan applications.
     * 
     * @return a list of all loan applications along with appropriate HTTP status code
     */
    @GetMapping("/api/loanapplication")
    public ResponseEntity<List<LoanApplication>> getAllLoanApplications() {
        List<LoanApplication> loanApplications = loanApplicationService.getAllLoanApplications();
        if (loanApplications != null) {
            return ResponseEntity.status(200).body(loanApplications);
        }
        return ResponseEntity.status(500).body(null);
    }

    /**
     * Handles HTTP PUT requests to update an existing loan application.
     * 
     * @param id the ID of the loan application to be updated
     * @param updatedLoanApplication the updated loan application details
     * @return the updated loan application along with appropriate HTTP status code
     */
    @PutMapping("/api/loanapplication/{loanapplicationId}")
    public ResponseEntity<LoanApplication> updateLoanApplication(@PathVariable Long loanapplicationId,@Valid @RequestBody LoanApplication updatedLoanApplication) {
        LoanApplication loanApplication = loanApplicationService.updateLoanApplication(loanapplicationId, updatedLoanApplication);
        if (loanApplication != null) {
            return ResponseEntity.status(200).body(loanApplication);
        }
        return ResponseEntity.status(500).body(null);
    }

    /**
     * Handles HTTP DELETE requests to delete a loan application by its ID.
     * 
     * @param id the ID of the loan application to be deleted
     * @return the deleted loan application along with appropriate HTTP status code
     */
    @DeleteMapping("/api/loanapplication/{loanapplicationId}")
    public ResponseEntity<LoanApplication> deleteLoanApplication(@PathVariable Long loanapplicationId) {
        LoanApplication deleted = loanApplicationService.deleteLoanApplication(loanapplicationId);
        if (deleted != null) {
            return ResponseEntity.status(200).body(deleted);
        }
        return ResponseEntity.status(500).body(null);
    }
}
