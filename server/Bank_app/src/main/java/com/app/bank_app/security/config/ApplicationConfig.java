//package com.app.bank_app.security.config;
//
//import com.app.bank_app.api.repositries.CustomerRepositry;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//@Configuration
//@RequiredArgsConstructor
//public class ApplicationConfig {
//
//    private final CustomerRepositry customerRepositry;
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return username -> customerRepositry.findByemail(username).get();
//    }
//}
