package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.LoanAlreadyExistsException;
import com.examly.springapp.model.Loan;
import com.examly.springapp.model.LoanApplication;
import com.examly.springapp.repository.LoanApplicationRepo;
import com.examly.springapp.repository.LoanRepo;

/**
 * Service implementation class for managing loan-related operations.
 * Annotated with `@Service` to indicate a service component.
 */
@Service
public class LoanServiceImpl implements LoanService {

    /**
     * Repository for loan operations.
     * Injected via constructor.
     */
    private LoanApplicationRepo loanApplicationRepo;
    private final LoanRepo loanRepo;

    /**
     * Constructor Injection
     *
     * @param loanRepo the `LoanRepo` repository to be injected.
     */
    public LoanServiceImpl(LoanRepo loanRepo,LoanApplicationRepo loanApplicationRepo) {
        this.loanRepo = loanRepo;
        this.loanApplicationRepo=loanApplicationRepo;
    }


    /**
     * Adds a new loan.
     *
     * @param loan the `Loan` entity to be added.
     * @return the saved `Loan` entity.
     * @throws LoanAlreadyExistsException if a loan with the same ID already exists.
     */
    @Override
    public Loan addLoan(Loan loan) throws LoanAlreadyExistsException {
        if (loanRepo.existsByLoanType(loan.getLoanType())) {
            throw new LoanAlreadyExistsException("Loan with this name already exists");
        }
        return loanRepo.save(loan);
    }


    /**
     * Retrieves a loan by ID.
     *
     * @param loanId the ID of the loan to be retrieved.
     * @return an `Optional` containing the `Loan` entity, if found.
     */
    @Override
    // @Cacheable(value = "LoanById", key = "#id")
    public Optional<Loan> getLoanById(Long loanId) {
        return loanRepo.findById(loanId);
    }

    /**
     * Retrieves all loans.
     *
     * @return a list of all `Loan` entities.
     */
    @Override
    // @Cacheable(value = "allLoans")

    public List<Loan> getAllLoans() {
        return loanRepo.findAll();
    }

    /**
     * Updates an existing loan by ID.
     *
     * @param loanId the ID of the loan to be updated.
     * @param updatedLoan the updated `Loan` entity.
     * @return the updated `Loan` entity, or null if not found.
     */
    @Override
    public Loan updateLoan(Long loanId, Loan updatedLoan) {
        Loan oldLoan = loanRepo.findById(loanId).orElse(null);
        if (oldLoan != null) {
            oldLoan.setLoanType(updatedLoan.getLoanType());
            oldLoan.setDescription(updatedLoan.getDescription());
            oldLoan.setDocumentsRequired(updatedLoan.getDocumentsRequired());
            oldLoan.setEligibility(updatedLoan.getEligibility());
            oldLoan.setInterestRate(updatedLoan.getInterestRate());
            oldLoan.setMaximumAmount(updatedLoan.getMaximumAmount());
            oldLoan.setRepaymentTenure(updatedLoan.getRepaymentTenure());
            return loanRepo.save(oldLoan);
        }else{
            return null;
        }
    }

    /**
     * Deletes a loan by ID.
     *
     * @param loanId the ID of the loan to be deleted.
     * @return the deleted `Loan` entity, or null if not found.
     */
    @Override
    @CacheEvict(value = "LoanById", key = "#loanId")
    public Loan deleteLoan(Long loanId) {
        Loan loan = loanRepo.findById(loanId).orElse(null);
        if (loan == null) {
            return null;
        }
        
        // Delete all related LoanApplications
        List<LoanApplication> loanApplications = loanApplicationRepo.findByLoan_LoanId(loanId);
        for (LoanApplication loanApplication : loanApplications) {
            loanApplicationRepo.delete(loanApplication);
        }
    
        // Now delete the Loan
        loanRepo.deleteById(loanId);
        return loan;
    }

}
