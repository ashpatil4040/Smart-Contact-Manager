package com.scm.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;
    //add contact page
    @RequestMapping("/add") 
    public String addContactView(Model model){
       
        ContactForm contactForm=new ContactForm();
        
        model.addAttribute("contactForm", contactForm);

        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm,BindingResult result,Authentication authentication,HttpSession session ) {
        
        //process the contact image

        //image processing
        //upload karneka code
        String filename = UUID.randomUUID().toString();
        String fileURL= imageService.uploadImage(contactForm.getContactImage(), filename);


        //process the form data


        //validate the form data
        
        if(result.hasErrors()){
            session.setAttribute("message", 
                    Message.builder()
                           .content("Please correct the following errors")
                           .type(MessageType.red)
                           .build());  

            return "user/add_contact";
        }

        String username=Helper.getEmailOfLoggedInUser(authentication);

        //form -> contact 
         User user =userService.getUserByEmail(username);
        
        Contact contact=new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setFavourite(contactForm.isFavourite());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setDescription(contactForm.getDescription());
        contact.setAddress(contactForm.getAddress());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(filename);

    
        contactService.save(contact);
        System.out.println(contactForm);

        session.setAttribute("message", 
           Message.builder()
                  .content("Contact added successfully")
                  .type(MessageType.green)     
                  .build());  

        return "redirect:/user/contacts/add";
    }
   

}
