package com.examly.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.LoanApplication;
import com.examly.springapp.model.User;

@Repository

public interface LoanApplicationRepo extends JpaRepository<LoanApplication,Long>{

    List<LoanApplication> findByUser(User user);

    // List<LoanApplication> findByLoanId(Long loanId);
    // @Query(value="SELECT * FROM loan_application where loan_id=?1",nativeQuery = true)
    // List<LoanApplication> getAllById(Long loanId);   
    List<LoanApplication> findByLoan_LoanId(Long loanId);

}
