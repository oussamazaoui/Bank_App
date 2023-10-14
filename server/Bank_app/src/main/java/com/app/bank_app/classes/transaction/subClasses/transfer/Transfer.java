package com.app.bank_app.classes.transaction.subClasses.transfer;

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
@DiscriminatorValue("transfer")
public class Transfer extends Transaction {
    @Id
    @GeneratedValue
    private Integer id;

}
