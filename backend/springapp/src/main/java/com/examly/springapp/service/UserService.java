package com.examly.springapp.service;
 
import java.util.List;

import com.examly.springapp.model.User;
import com.examly.springapp.model.dto.LoginDTO;
 
public interface UserService {
 
    User registerUser(User user);
 
    LoginDTO loginUser(User user);
 
    List<User>getAllUsers();
 
    User getUserById(Long id);
}