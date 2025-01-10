package com.scm.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;

    //user dashboard page
    @GetMapping("/dashboard")
    public String userDashboard() {
        
        System.out.println("user dashboard");
        return "user/dashboard";
    }
    
    //user profile page
    @GetMapping("/profile")
    public String userProfile(Model model,Authentication authentication) {
      
        
        return "user/profile";
    }

    //user

}
