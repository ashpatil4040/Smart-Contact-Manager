package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserFrom;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {
    
    @Autowired
    private UserService userService;
    

    // home page
    @GetMapping("/")
    public String Home() {
      return "redirect:/home";
    }


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
    public String processRegister(@Valid @ModelAttribute("userForm") UserFrom userForm, BindingResult rBindingResult ,HttpSession session) {
         
        System.out.println("UserForm: "+userForm);
       // validation
        if(rBindingResult.hasErrors()) {
            return "register";
        }
        // UserFrom--->User
        // User user = User.builder()
        //                 .name(userForm.getName())
        //                 .email(userForm.getEmail())
        //                 .password(userForm.getPassword())
        //                 .about(userForm.getAbout())
        //                 .phoneNumber(userForm.getPhoneNumber())
        //                 .profilePic("src\\main\\resources\\static\\images\\default_ProfilePic.png")
        //                 .build();
       
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("src\\main\\resources\\static\\images\\default_ProfilePic.png");
        User savedUser = userService.saveUser(user);
        
        Message message= Message.builder()
               .content("User registered successfully")
               .type(MessageType.green)
               .build();

        session.setAttribute("message", message);
        return "redirect:/register";
    } 
}
