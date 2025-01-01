package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

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
    public String register() {
        return "register";
    }
    
}
