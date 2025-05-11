package com.examly.springapp.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.User;
import com.examly.springapp.model.dto.LoginDTO;
import com.examly.springapp.service.UserService;
 
@RestController
@RequestMapping("/api")
public class AuthController {
 
    @Autowired
    private final UserService userService;
 
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
   
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        System.out.println("AuthController: "+user);
        User registerUser = userService.registerUser(user);
        if(registerUser!=null){
            return ResponseEntity.status(201).body(registerUser);
        }
        return ResponseEntity.status(409).build();
    }
 
    @PostMapping("/login")
    public ResponseEntity<LoginDTO> loginUser(@RequestBody User user){
        LoginDTO loginUser = userService.loginUser(user);
        if(loginUser!=null){
            return ResponseEntity.status(201).body(loginUser);
        }
        return ResponseEntity.status(401).build();
    }
 
    @GetMapping("/users")
    public ResponseEntity<List<User>>getAllUsers(){
        List<User>allUsers=userService.getAllUsers();
        if (allUsers != null) {
            return ResponseEntity.status(200).body(allUsers);
        } else {
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<User>getUserById(@PathVariable Long userId){
        User userFound=userService.getUserById(userId);
        if(userFound!=null){
            return ResponseEntity.status(200).body(userFound);
        }
        return ResponseEntity.status(500).body(null);
 
    }
   
}