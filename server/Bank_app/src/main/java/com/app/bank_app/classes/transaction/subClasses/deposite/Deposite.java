package com.app.bank_app.classes.transaction.subClasses.deposite;

import com.app.bank_app.classes.transaction.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
