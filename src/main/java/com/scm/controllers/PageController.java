package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserFrom;
import com.scm.services.UserService;

@Controller
public class PageController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/home")
    public String Home(Model model) {
        System.out.println("Home Page");

        // send data to home.html
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtubeChannel", "https://www.youtube.com/channel/UC785368v212041307");
        model.addAttribute("address", "India");
        return "home";
    }

    // about page

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        System.out.println("About Page");
        model.addAttribute("isLogin", false);;
        return "about";
    }
    // services page
    @RequestMapping("/services")
    public String servicesPage(Model model) {
        System.out.println("Services Page");
        return "services";
    }
    
    //contact page
    @GetMapping("/contact")
    public String contact() {
        System.out.println("Contact Page");
        return new String("contact");
    }
    
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        
        UserFrom userForm = new UserFrom();
        // set default value
        // userForm.setName("John Doe");
        // userForm.setEmail("john@gmail.com");
        // userForm.setPassword("123456");
        // userForm.setAbout("I am a software developer");
        // userForm.setPhoneNumber("1234567890");
        model.addAttribute("userForm", userForm);
        
        return "register";
    }
    
    //processing register form
    @PostMapping("/do-register")
    public String processRegister(@ModelAttribute UserFrom userForm) {
         
        // UserFrom--->User
        User user = User.builder()
                        .name(userForm.getName())
                        .email(userForm.getEmail())
                        .password(userForm.getPassword())
                        .about(userForm.getAbout())
                        .phoneNumber(userForm.getPhoneNumber())
                        .profilePic("src\\main\\resources\\static\\images\\default_ProfilePic.png")
                        .build();

        User savedUser = userService.saveUser(user);
        
        return "redirect:/register";
    } 
}
