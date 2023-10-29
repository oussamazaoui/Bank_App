package com.app.bank_app.api.ressources;

import com.app.bank_app.security.enums.Role;
import com.app.bank_app.api.models.Account;
import com.app.bank_app.api.models.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
        private Integer id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private LocalDate birthday;
        private String id_Card;
        private String email;
        private Role role;
        private Integer age;
        private List<Account> accounts;
        private List<Transaction> transactions;

}
