package com.example.studybud.Service;

import com.example.studybud.Repository.RoleRepo;
import com.example.studybud.entity.Role;
import com.example.studybud.entity.User;
import com.example.studybud.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    /*@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    public User addUser(User user) {
       // String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        String encodedPassword = user.getPassword();
        user.setPassword(encodedPassword);
        return userRepo.save(user);
    }


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }


    public User updateUser(Long id, User userDetails) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            user.setRole(userDetails.getRole());
            userRepo.save(user);
        }
        return user;
    }


    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
    public User addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
        return userRepo.save(user);
    }

}
