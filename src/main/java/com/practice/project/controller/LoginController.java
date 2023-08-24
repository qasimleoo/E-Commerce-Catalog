package com.practice.project.controller;

import com.practice.project.repository.RoleRepo;
import com.practice.project.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("register")
    public String registerGet(){
        return "register";
    }
}