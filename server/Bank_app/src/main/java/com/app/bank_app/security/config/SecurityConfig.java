package com.app.bank_app.security.config;

import com.app.bank_app.security.enums.Permission;
import com.app.bank_app.security.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.app.bank_app.security.enums.Permission.*;
import static com.app.bank_app.security.enums.Role.admin;
import static com.app.bank_app.security.enums.Role.user;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthentificationFilter jwtAuthentificationFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(req->req.requestMatchers("api/auth/login","api/auth/register")
                       .permitAll()
                       .requestMatchers("api/customer/**").hasAnyRole(admin.name(), user.name())
//                       .requestMatchers(HttpMethod.GET, "api/customer/**").hasAuthority(CUSTOMER_READ.name())
//                       .requestMatchers(HttpMethod.DELETE,"api/customer/**").hasAuthority(CUSTOMER_DELETE.name())
//                       .requestMatchers(HttpMethod.POST,"api/customer/**").hasAuthority(CUSTOMER_CREATE.name())
//                       .requestMatchers(HttpMethod.PUT,"api/customer/**").hasAuthority(CUSTOMER_UPDATE.name())
//                       .requestMatchers("api/account/**").hasAnyRole(admin.name(),user.name())
//                       .requestMatchers(HttpMethod.GET,"api/account/**").hasAuthority(ACCOUNT_READ.name())
//                       .requestMatchers(HttpMethod.POST,"api/account/**").hasAuthority(ACCOUNT_CREATE.name())
//                       .requestMatchers(HttpMethod.DELETE,"api/account/**").hasAuthority(ACCOUNT_DELETE.name())
//                       .requestMatchers("api/transaction/**").hasAnyRole(admin.name(),user.name())
//                       .requestMatchers(HttpMethod.GET,"api/transaction/**").hasAuthority(TRANSACTION_READ.name())
//                       .requestMatchers(HttpMethod.POST,"api/transaction/**").hasAuthority(TRANSACTION_CREATE.name())
                       .anyRequest().authenticated())
               .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authenticationProvider(authenticationProvider)
               .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class);
       return http.build();

    }
}
