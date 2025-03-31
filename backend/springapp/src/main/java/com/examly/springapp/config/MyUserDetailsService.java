package com.examly.springapp.config;
 
import java.util.Collections;
 
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
 
@Component
public class MyUserDetailsService implements UserDetailsService {
 
    private UserRepo userRepository;
 
    public MyUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found with email: " + email);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getUserRole());
        return new UserPrinciple(user.getEmail(), user.getPassword(), Collections.singletonList(authority));
    }
}