package com.app.bank_app.classes.transaction;

import com.app.bank_app.classes.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "transaction_type",
        discriminatorType = DiscriminatorType.STRING
)
@Table(name = "transaction")
public abstract class Transaction {
    @Id
    @GeneratedValue
    private Integer id;
    private double amount;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Source source;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @PrePersist
    private void setDateAndSource() {

        this.date = new Date();
        this.source=Source.online;
    }

}
