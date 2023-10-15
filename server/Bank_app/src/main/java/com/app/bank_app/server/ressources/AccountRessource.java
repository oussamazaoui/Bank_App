package com.app.bank_app.server.ressources;

import com.app.bank_app.server.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRessource {
    private Integer id;
    private String accountName;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Integer accountNumber;
    private double balance;
    private Date openingDate;
    private boolean isActive;
    private Integer customer_id;

}
