package com.examly.springapp.model.dto;
 
 
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
 
public class LoanApplicationDTO {
 
    @Lob
    @Column(length=1000000)
    private String file;
 
    private Long userId;
    private Long loanId;
 
    @NotBlank(message = "Course is required")
    @Size(max = 20, message = "Course must be less than 20 characters")
    private String course;
 
    @NotBlank(message = "Institution is required")
    private String institution;
 
    @Min(value = 0, message = "Tuition fee must be greater than or equal to 0")
    private double tuitionFee;
   
    @Min(value = 0, message = "Loan status must be greater than or equal to 0")
    @Max(value = 5, message = "Loan status must be less than or equal to 5")
    private int loanStatus;
    @NotNull(message = "Address cannot be null")
    @NotEmpty(message = "Address cannot be empty")
    @Size(min = 5, max = 100, message = "Address length must be between 5 and 100 characters")
    private String address;
   
   
    public LoanApplicationDTO() {
    }
    public LoanApplicationDTO(String file, Long userId, Long loanId, String course, String institution,
            double tuitionFee, int loanStatus, String address) {
        this.file = file;
        this.userId = userId;
        this.loanId = loanId;
        this.course = course;
        this.institution = institution;
        this.tuitionFee = tuitionFee;
        this.loanStatus = loanStatus;
        this.address = address;
    }
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getLoanId() {
        return loanId;
    }
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
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
 
   
}