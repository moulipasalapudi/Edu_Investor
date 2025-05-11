/*
 * LoanApplicationServiceImpl class
 * This service contains all the logic to perform loan application-related functionalities.
 */
package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.LoanApplicationNotFoundException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.Loan;
import com.examly.springapp.model.LoanApplication;
import com.examly.springapp.model.User;
import com.examly.springapp.model.dto.LoanApplicationDTO;
import com.examly.springapp.repository.LoanApplicationRepo;
import com.examly.springapp.repository.LoanRepo;
import com.examly.springapp.repository.UserRepo;

/**
 * Implementation of the LoanApplicationService interface.
 * This class provides the logic for creating, retrieving, updating, and
 * deleting loan applications.
 * 
 * @Author [Name]
 */
@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepo loanApplicationRepo;
    private final UserRepo userRepo;
    private final LoanRepo loanRepo;

    // Constructor to inject the dependencies
    public LoanApplicationServiceImpl(LoanApplicationRepo loanApplicationRepo, UserRepo userRepo,LoanRepo loanRepo) {
        this.loanApplicationRepo = loanApplicationRepo;
        this.userRepo = userRepo;
        this.loanRepo=loanRepo;
    }

    /**
     * Adds a new LoanApplication.
     * @param loanApplicationDTO the loan application DTO to be added
     * @return the added LoanApplication object
     * @throws UserNotFoundException if the user is not found
     * @throws LoanApplicationNotFoundException if the loan is not found
    */
    @Override
    public LoanApplication addLoanApplication(LoanApplicationDTO loanApplicationDTO) throws UserNotFoundException, LoanApplicationNotFoundException {
        User existingUser = userRepo.findById(loanApplicationDTO.getUserId()).orElse(null);
        if (existingUser == null) {
            throw new UserNotFoundException("User is Not Found");
        }
        Loan existingLoan = loanRepo.findById(loanApplicationDTO.getLoanId()).orElse(null);
        if (existingLoan == null) {
            throw new LoanApplicationNotFoundException("Loan with ID " + loanApplicationDTO.getLoanId() + " not found!");
        }

        LoanApplication newLoanApplication = new LoanApplication();
        newLoanApplication.setCourse(loanApplicationDTO.getCourse());
        newLoanApplication.setInstitution(loanApplicationDTO.getInstitution());
        newLoanApplication.setTuitionFee(loanApplicationDTO.getTuitionFee());
        newLoanApplication.setLoanStatus(loanApplicationDTO.getLoanStatus());
        newLoanApplication.setAddress(loanApplicationDTO.getAddress());
        newLoanApplication.setFile(loanApplicationDTO.getFile());
        newLoanApplication.setSubmissionDate(LocalDate.now());
        newLoanApplication.setUser(existingUser);
        newLoanApplication.setLoan(existingLoan);

        loanApplicationRepo.save(newLoanApplication);
        return newLoanApplication;
    }

    /**
     * Retrieves loan applications by user ID from the repository.
     * 
     * @param userId the ID of the user whose loan applications are to be retrieved
     * @return the list of loan applications for the specified user or null if none
     *         are found
     */
    @Override
    // @Cacheable(value = "loanApplicationByUserId", key = "#userId")
    public List<LoanApplication> getLoanApplicationByUserId(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        return user.getLoanApplication();
    }

    /**
     * Retrieves a loan application by its ID from the repository.
     * 
     * @param loanapplicationId the ID of the loan application to be retrieved
     * @return the loan application with the specified ID or null if not found
     */
    @Override
    // @Cacheable(value = "loanApplicationById", key = "#loanapplicationId")
    public Optional<LoanApplication> getLoanApplicationById(Long loanapplicationId) {
        Optional<LoanApplication> loanApplication = loanApplicationRepo.findById(loanapplicationId);
        return loanApplication;
    }

    /**
     * Retrieves all loan applications from the repository.
     * 
     * @return a list of all loan applications
     */
    @Override
    // @Cacheable(value = "allLoanApplications")
    public List<LoanApplication> getAllLoanApplications() {
        return loanApplicationRepo.findAll();
    }

    /**
     * Updates an existing loan application with the provided details.
     * 
     * @param loanApplicationId      the ID of the loan application to be updated
     * @param updatedLoanApplication the updated loan application details
     * @return the updated loan application or null if not found
     */
    @Override
    public LoanApplication updateLoanApplication(Long loanApplicationId, LoanApplication updatedLoanApplication) {
        LoanApplication existingLoanApplication = loanApplicationRepo.findById(loanApplicationId).orElse(null);
        if (existingLoanApplication != null) {
            existingLoanApplication.setSubmissionDate(updatedLoanApplication.getSubmissionDate());
            existingLoanApplication.setCourse(updatedLoanApplication.getCourse());
            existingLoanApplication.setInstitution(updatedLoanApplication.getInstitution());
            existingLoanApplication.setTuitionFee(updatedLoanApplication.getTuitionFee());
            existingLoanApplication.setLoanStatus(updatedLoanApplication.getLoanStatus());
            existingLoanApplication.setAddress(updatedLoanApplication.getAddress());
            existingLoanApplication.setFile(updatedLoanApplication.getFile());
            existingLoanApplication.setUser(updatedLoanApplication.getUser());
            existingLoanApplication.setLoan(updatedLoanApplication.getLoan());
            return loanApplicationRepo.save(existingLoanApplication);
        } else {
            return null;
        }
    }

    /**
     * Deletes an existing loan application by its ID.
     * 
     * @param loanApplicationId the ID of the loan application to be deleted
     * @return the deleted loan application or null if not found
     */
    @Override
    // @CacheEvict(value = "loanApplicationById", key = "#loanApplicationId")
    public LoanApplication deleteLoanApplication(Long loanApplicationId) {
        if (loanApplicationRepo.existsById(loanApplicationId)) {
            LoanApplication existingLoanApplication = loanApplicationRepo.findById(loanApplicationId).orElse(null);
            loanApplicationRepo.deleteById(loanApplicationId);
            return existingLoanApplication;
        }
        return null;
    }
}
