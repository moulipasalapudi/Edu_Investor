package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.exception.LoanApplicationNotFoundException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.LoanApplication;
import com.examly.springapp.model.dto.LoanApplicationDTO;

public interface LoanApplicationService {
    LoanApplication addLoanApplication (LoanApplicationDTO loanApplication) throws UserNotFoundException, LoanApplicationNotFoundException; 
    List<LoanApplication> getLoanApplicationByUserId (Long userId); 
    Optional<LoanApplication> getLoanApplicationById (Long loanapplicationId); 
    List<LoanApplication> getAllLoanApplications (); 
    LoanApplication updateLoanApplication (Long loanApplicationId, LoanApplication updatedLoanApplication); 
    LoanApplication deleteLoanApplication (Long loanApplicationId); 
}
