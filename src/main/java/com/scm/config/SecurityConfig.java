package com.scm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class SecurityConfig {

     //user creation and login using java code with in memory service
      
     @Bean
     public UserDetailsManager userDetailsManager() {

        UserDetails user1  = User.withDefaultPasswordEncoder()
                                .username("admin123")
                                .password("admin123")
                                .roles("ADMIN","USER") 
                                .build();
        UserDetails user2  = User.withDefaultPasswordEncoder()
                                .username("user123") 
                                .password("user123")
                                // .roles("USER") 
                                .build();
         var InMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2);
         return InMemoryUserDetailsManager;
     }
}
