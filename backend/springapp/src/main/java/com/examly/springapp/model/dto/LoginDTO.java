package com.examly.springapp.model.dto;
 
public class LoginDTO {
    private String email;
 
    private Long userId;
    private String token;
    private String userRole;
   
    public LoginDTO() {
    }
    public LoginDTO(String email, Long userId, String token, String userRole) {
        this.email = email;
        this.userId = userId;
        this.token = token;
        this.userRole = userRole;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
   
}
 