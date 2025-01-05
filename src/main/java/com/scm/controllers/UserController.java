package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    //user dashboard page
    @GetMapping("/dashboard")
    public String userDashboard() {
        
        System.out.println("user dashboard");
        return "user/dashboard";
    }
    
    //user profile page
    @GetMapping("/profile")
    public String userProfile() {

        System.out.println("user profile");
        return "user/profile";
    }

    //user

}
