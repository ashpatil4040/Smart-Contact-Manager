package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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


    // configuration for authentication provider using dao authentication provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        
        //set the user details service ka object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        //set the password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    //security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // configuration

        // urls configure kiay hai ki koun se public rangenge aur koun se private
        // rangenge
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        // form default login
        // agar hame kuch bhi change karna hua to hama yaha ayenge: form login se
        // related
        httpSecurity.formLogin(formLogin -> {

            formLogin.loginPage("/login")
                      .loginProcessingUrl("/authenticate")
                      .defaultSuccessUrl("/user/dashboard", true)
                      .failureUrl("/login?error=true")
            // formLogin.defaultSuccessUrl("/home");
                      .usernameParameter("email")
                      .passwordParameter("password");

            
            // formLogin.failureHandler(new AuthenticationFailureHandler() {
                
            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            //             AuthenticationException exception) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
            //     }
            // });

            // formLogin.successHandler(new AuthenticationSuccessHandler() {

            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //     }
                
            // });
        }

        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        return httpSecurity.build();
    }
        



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }
}
