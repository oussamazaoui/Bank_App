package com.app.bank_app.security.auth;

import com.app.bank_app.security.enums.Role;
import com.app.bank_app.api.models.Customer;
import com.app.bank_app.api.services.CustomerService;
import com.app.bank_app.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentificationService {
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthentificationResponse register(RegisterRequest request){
        var user= Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .birthday(request.getBirthday())
                .id_Card(request.getId_Card())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.user)
                .build();
        customerService.newCustomer(user);
        var jwt=jwtService.generateToken(user);
        return AuthentificationResponse.builder()
                .token(jwt)
                .id(customerService.getCustomerByEmail(request.getEmail()).get().getId())
                .build();
    }
    public AuthentificationResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user=customerService.getCustomerByEmail(request.getEmail()).orElseThrow();
        var jwt=jwtService.generateToken(user);
        return AuthentificationResponse.builder()
                .token(jwt)
                .id(user.getId())
                .build();
    }
}
