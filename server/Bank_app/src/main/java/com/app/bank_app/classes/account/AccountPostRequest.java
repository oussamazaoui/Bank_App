package com.app.bank_app.classes.account;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountPostRequest {
    private String accountName;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}
