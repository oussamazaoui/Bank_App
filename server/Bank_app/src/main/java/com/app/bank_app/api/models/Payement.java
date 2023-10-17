package com.app.bank_app.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@DiscriminatorValue("payement")
public class Payement extends Transaction {
    private String beneficiary;
    private Integer accountNumber;
    @Column(unique = true)
    private String accountName;
    private String reference;
}
