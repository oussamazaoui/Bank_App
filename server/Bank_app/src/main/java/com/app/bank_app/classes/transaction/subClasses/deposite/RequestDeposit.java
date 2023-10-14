package com.app.bank_app.classes.transaction.subClasses.deposite;

import com.app.bank_app.classes.transaction.Source;
import com.app.bank_app.classes.transaction.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RequestDeposit {
    private String accountName;
    private Double amount;
    private Integer customer_id;
}

