package com.examly.springapp.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.model.User;
import com.examly.springapp.model.dto.LoginDTO;
import com.examly.springapp.repository.UserRepo;
 
@Service
public class UserServiceImpl implements UserService{
 
    private final UserRepo userRepo;
 
    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
 
    @Autowired
    private JwtUtils jwtUtils;
 
    @Override
    public User registerUser(User user){
        User existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser != null){
            // throw new EmailAlreadyExistsException("This Email.Id already exists.");
            return null;
        }
        return userRepo.save(user);
    }
   
   
    @Override
    public LoginDTO loginUser(User user){
    User existingUser = userRepo.findByEmail(user.getEmail());
    if(existingUser != null){
    System.out.print("\n"+existingUser+"\n");
    if(existingUser.getPassword().equals(user.getPassword())){
        
    String token = jwtUtils.generateToken(existingUser.getEmail(),existingUser.getUserRole(), existingUser.getUsername(), existingUser.getUserId());
    return new LoginDTO(existingUser.getEmail(), existingUser.getUserId(), token, existingUser.getUserRole());
    }else{
        // throw new PageNotFoundException("password mismatch");
        return null;
    }
}
// throw new PageNotFoundException("need to register before login");
return null;
}
 
 
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
 
 
    @Override
    public User getUserById(Long id) {
       User u= userRepo.findById(id).orElse(null);
       if(u==null)return null;
       return u;
    }
 
}