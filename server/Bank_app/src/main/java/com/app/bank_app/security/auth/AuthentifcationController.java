package com.app.bank_app.security.auth;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "api/auth")
@RequiredArgsConstructor
public class AuthentifcationController {
    private final AuthentificationService authentificationService;
    @PostMapping(path = "/register")
    public ResponseEntity<AuthentificationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authentificationService.register(request));
    }
    @PostMapping(path = "/login")
    public ResponseEntity<AuthentificationResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authentificationService.login(request));
    }
}
