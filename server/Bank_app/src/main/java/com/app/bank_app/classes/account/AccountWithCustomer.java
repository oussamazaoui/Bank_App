package com.app.bank_app.classes.account;

import com.app.bank_app.classes.customer.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountWithCustomer extends AccountRessource {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String id_Card;
    private Integer age;


}
