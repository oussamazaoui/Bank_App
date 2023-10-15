package com.app.bank_app.server.models;

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
@DiscriminatorValue("deposite")
public class Deposite extends Transaction {
    private String accountName;

}
