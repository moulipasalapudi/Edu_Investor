package com.examly.springapp.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;


// Entity class representing a LoanApplication.
// Annotated with `@Entity` to map the class to a database table.

@Entity

public class LoanApplication {

    // Unique identifier for a LoanApplication.
    // Annotated with `@Id` to denote primary key.
    // Uses `@GeneratedValue` with the strategy `GenerationType.IDENTITY` for automatic ID generation.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanApplicationId;
    private LocalDate submissionDate;
    private String course;
    private String institution;
    private double tuitionFee;
    private int loanStatus;
    private String address;


    @Lob
    @Column(length=1000000)
    private String file;

    /**
     * User associated with the LoanApplication.
     * Annotated with `@ManyToOne` to denote the many-to-one relationship with the `User` entity.
     */
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;


    /**
     * User associated with the LoanApplication.
     * Annotated with `@ManyToOne` to denote the many-to-one relationship with the `Loan` entity.
     */
    @ManyToOne
    @JoinColumn(name = "loanId")
    // @JsonBackReference
    private Loan loan;
    

    // Default constructor to initialize the LoanApplication object.
    public LoanApplication() {
    }

    // Parameterized constructor to initialize the LoanApplication object.
    public LoanApplication(Long loanApplicationId, LocalDate submissionDate, String course, String institution,
            double tuitionFee, int loanStatus, String address, String file, User user, Loan loan) {
        this.loanApplicationId = loanApplicationId;
        this.submissionDate = submissionDate;
        this.course = course;
        this.institution = institution;
        this.tuitionFee = tuitionFee;
        this.loanStatus = loanStatus;
        this.address = address;
        this.file = file;
        this.user = user;
        this.loan = loan;
    }

    // Getter and setter methods for LoanApplicationId.
    /**
     * Gets the LoanApplication ID.
     * @return the LoanApplication ID
     */
    public Long getLoanApplicationId() {
        return loanApplicationId;
    }

    /**
     * Sets the LoanApplication ID.
     * @param LoanApplicationId the LoanApplication ID to set
     */
    public void setLoanApplicationId(Long loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }
    public LocalDate getSubmissionDate() {
        return submissionDate;
    }
    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getInstitution() {
        return institution;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    public double getTuitionFee() {
        return tuitionFee;
    }
    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }
    public int getLoanStatus() {
        return loanStatus;
    }
    public void setLoanStatus(int loanStatus) {
        this.loanStatus = loanStatus;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Loan getLoan() {
        return loan;
    }
    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    

}
