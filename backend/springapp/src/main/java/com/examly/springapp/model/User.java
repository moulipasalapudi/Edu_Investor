package com.examly.springapp.model;
 
import java.util.ArrayList;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonManagedReference;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
 
// Entity class representing a User.
// Annotated with `@Entity` to map the class to a database table.
@Entity
@Table(name = "users")
public class User {
 
    // Unique identifier for a User.
    // Annotated with `@Id` to denote primary key.
    // Uses `@GeneratedValue` with the strategy `GenerationType.IDENTITY` for
    // automatic ID generation.
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String password;
    private String username;
    private String mobileNumber;
    private String userRole;
   
    @OneToMany(mappedBy = "user")
    private List<LoanApplication> loanApplication = new ArrayList<>();
 
    public User() {
    }
 
    public User(Long userId, String email, String password, String username, String mobileNumber, String userRole) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.userRole = userRole;
    }
 
    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getMobileNumber() {
        return mobileNumber;
    }
 
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
 
    public String getUserRole() {
        return userRole;
    }
 
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
 
    public List<LoanApplication> getLoanApplication() {
        return loanApplication;
    }
 
    public void setLoanApplication(List<LoanApplication> loanApplication) {
        this.loanApplication = loanApplication;
    }
 
}