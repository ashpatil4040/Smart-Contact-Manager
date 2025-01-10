package com.scm.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;

@ControllerAdvice
public class RootController {
   
    private Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;


    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
       
        if (authentication == null) {
            return;
        }
        
        System.out.println("add logged in user information to model");

        String username= Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User Logged in:{}" + username);

        //database se user ki details fetch karni hai: email, name, address etc

        User user= userService.getUserByEmail(username);
        
        logger.info("User Details: {}" +  user.getEmail());

        model.addAttribute("LoggedInUser", user);
        System.out.println("user profile");
    }

}
