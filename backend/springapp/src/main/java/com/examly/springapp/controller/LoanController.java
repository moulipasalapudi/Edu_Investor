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

import com.examly.springapp.model.Loan;
import com.examly.springapp.service.LoanService;

import jakarta.validation.Valid;;

/**
 * Controller class for managing loan-related endpoints.
 * Annotated with `@RestController` to indicate a RESTful controller.
 */
@RestController
public class LoanController {

    /**
     * Service for loan operations.
     * Injected via constructor.
     */
    private final LoanService loanService;

    // Constructor Injection
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    
    /**
     * Adds a new loan.
     *
     * @param loan the `Loan` entity provided in the request body.
     * @return a `ResponseEntity` containing the saved `Loan` entity or an error status.
     */
    @PostMapping("/api/loan")
    public ResponseEntity<Loan> addLoan(@Valid @RequestBody Loan loan) {
        Loan newLoan = loanService.addLoan(loan);
        if (newLoan != null) {
            return ResponseEntity.status(201).body(newLoan);
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Retrieves a loan by ID.
     *
     * @param loanId the ID of the loan to be retrieved.
     * @return a `ResponseEntity` containing the `Loan` entity or an error status.
     */
    @GetMapping("/api/loan/{loanId}")
    public ResponseEntity<Optional<Loan>> getLoanById(@PathVariable Long loanId) {
        Optional<Loan> loan = loanService.getLoanById(loanId);
        if (loan.isPresent()) {
            return ResponseEntity.status(200).body(loan);
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Retrieves all loans.
     *
     * @return a `ResponseEntity` containing a list of all `Loan` entities.
     */
    @GetMapping("/api/loan")
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        if (loans != null) {
            return ResponseEntity.status(200).body(loans);
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Updates an existing loan by ID.
     *
     * @param loanId the ID of the loan to be updated.
     * @param updatedLoan the updated `Loan` entity provided in the request body.
     * @return a `ResponseEntity` containing the updated `Loan` entity or an error status.
     */
    @PutMapping("/api/loan/{loanId}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long loanId,@Valid @RequestBody Loan updatedLoan) {
        Loan loan = loanService.updateLoan(loanId, updatedLoan);
        if (loan != null) {
            return ResponseEntity.status(200).body(loan);
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Deletes a loan by ID.
     *
     * @param loanId the ID of the loan to be deleted.
     * @return a `ResponseEntity` indicating the success or failure of the operation.
     */
    @DeleteMapping("/api/loan/{loanId}")
    public ResponseEntity<Loan> deleteLoan(@PathVariable Long loanId) {
        Loan loan = loanService.deleteLoan(loanId);
        if (loan != null) {
            return ResponseEntity.status(200).body(loan);
        } else {
            return ResponseEntity.status(500).build();
        }
    }           
}
