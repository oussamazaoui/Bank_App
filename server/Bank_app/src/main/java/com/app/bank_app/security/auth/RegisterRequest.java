package com.app.bank_app.security.auth;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String id_Card;
    private String email;
    private String password;

    @Builder.Default
    private LocalDate birthday = null; // Default value for birthday is the current date
    @Builder.Default
    private String phoneNumber=null;

}
