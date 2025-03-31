package com.examly.springapp.model.dto;
 
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
 
public class LoanDTO {
   
    @NotNull(message = "Loan ID is required")
    private Long loanId;
 
    @NotBlank(message = "Loan type is required")
    @Size(max = 100, message = "Loan type must be less than 100 characters")
    private String loanType;
 
    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;
 
    @Positive(message = "Interest rate must be positive")
    private double interestRate;
 
    @PositiveOrZero(message = "Maximum amount must be zero or positive")
    private int maximumAmount;
 
    @Min(value = 1, message = "Repayment tenure must be at least 1 year")
    @Max(value = 30, message = "Repayment tenure must be less than or equal to 30 years")
    private int repaymentTenure;
 
    @NotBlank(message = "Eligibility criteria are required")
    @Size(max = 300, message = "Eligibility criteria must be less than 300 characters")
    private String eligibility;
 
    @NotBlank(message = "Documents required are needed")
    @Size(max = 300, message = "Documents required must be less than 300 characters")
    private String documentsRequired;
 
 
   
    public Long getLoanId() {
        return loanId;
    }
 
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
 
    public String getLoanType() {
        return loanType;
    }
 
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public double getInterestRate() {
        return interestRate;
    }
 
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
 
    public int getMaximumAmount() {
        return maximumAmount;
    }
 
    public void setMaximumAmount(int maximumAmount) {
        this.maximumAmount = maximumAmount;
    }
 
    public int getRepaymentTenure() {
        return repaymentTenure;
    }
 
    public void setRepaymentTenure(int repaymentTenure) {
        this.repaymentTenure = repaymentTenure;
    }
 
    public String getEligibility() {
        return eligibility;
    }
 
    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }
 
    public String getDocumentsRequired() {
        return documentsRequired;
    }
 
    public void setDocumentsRequired(String documentsRequired) {
        this.documentsRequired = documentsRequired;
    }
}