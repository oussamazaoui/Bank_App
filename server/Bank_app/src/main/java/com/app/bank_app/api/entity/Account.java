package com.app.bank_app.api.entity;

import com.app.bank_app.api.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue
    private Integer id;
    private String accountName;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Integer accountNumber;
    private double balance;
    private Date openingDate;
    private boolean isActive;
    private static int accountNum=100210333;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @PrePersist
    private void setOpeningDateToCurrentDateAndAccountNumber() {
        this.openingDate = new Date();
        this.accountNumber = accountNum++;
    }

//    public boolean deposit(Long balance){
//        if(balance>0){
//           this.balance=balance;
//           return true;
//        }else {
//            return false;
//        }
//    }


}
