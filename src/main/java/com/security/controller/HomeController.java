package com.security.controller;

import com.security.entity.User;
import com.security.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //for testing
    @GetMapping("/home")
    public String home(){
        return "home page";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin page";
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return customUserDetailService.addUser(user);
    }
    @GetMapping("/user")
    public List<User> getAllUsers(){
        return customUserDetailService.getUser();
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return customUserDetailService.updateUser(user);
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return customUserDetailService.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        return customUserDetailService.deleteUser(id);
    }

}
