package com.security.service;

import com.security.config.CustomUserDetails;
import com.security.entity.User;
import com.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not Found");
        }
        return new CustomUserDetails(user);
    }

    public User addUser(User user){
        return userRepository.save(user);
    }
    public List<User> getUser(){
        return userRepository.findAll();
    }

    public User updateUser(User user){
        User user1=userRepository.findById(user.getId()).orElse(null);
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setRole(user.getRole());
        return userRepository.save(user1);
    }
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "deleted successfully";
    }

}
