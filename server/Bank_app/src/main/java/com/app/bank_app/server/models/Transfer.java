package com.app.bank_app.server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("transfer")
public class Transfer extends Transaction {
    private String accountName;
    private String otherAccountName;

}
