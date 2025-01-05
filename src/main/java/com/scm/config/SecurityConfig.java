package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scm.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

     //user creation and login using java code with in memory service
      
    //  @Bean
    //  public UserDetailsManager userDetailsManager() {

    //     UserDetails user1  = User.withDefaultPasswordEncoder()
    //                             .username("admin123")
    //                             .password("admin123")
    //                             .roles("ADMIN","USER") 
    //                             .build();
    //     UserDetails user2  = User.withDefaultPasswordEncoder()
    //                             .username("user123") 
    //                             .password("user123")
    //                             // .roles("USER") 
    //                             .build();
    //      var InMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2);
    //      return InMemoryUserDetailsManager;
    //  }
    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        
        //set the user details service ka object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        //set the password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }
}
