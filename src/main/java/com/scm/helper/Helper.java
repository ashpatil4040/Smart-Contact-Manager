package com.scm.helper;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
  
      public static String getEmailOfLoggedInUser(Authentication authentication){ 
           

        //agar hamne email id and password se login kiya hai toh: email kaise nikalenge
        if(authentication instanceof OAuth2AuthenticationToken){

            var aOAuth2Authentication=(OAuth2AuthenticationToken)authentication;
            var clientid= aOAuth2Authentication.getAuthorizedClientRegistrationId();
            
            var oauth2User=(OAuth2User)authentication.getPrincipal();
            
            String username="";

            if(clientid.equalsIgnoreCase("google"))
            {
                // sign in with google
                System.out.println("Google User");
                username = oauth2User.getAttribute("email").toString();
               


            }else if(clientid.equalsIgnoreCase("github")){
                // sign in with github
                System.out.println("Github User");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString()+"@gmail.com";


            }
            


            return username;
        
        }else{
           
            // sign in with email and password
            return authentication.getName();
          }


        
          
           
        }

}