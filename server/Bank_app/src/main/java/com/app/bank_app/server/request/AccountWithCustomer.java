package com.app.bank_app.server.request;

import com.app.bank_app.server.ressources.AccountRessource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
