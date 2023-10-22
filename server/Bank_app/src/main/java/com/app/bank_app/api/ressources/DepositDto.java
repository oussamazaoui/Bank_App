package com.app.bank_app.api.ressources;

import com.app.bank_app.api.enums.Source;
import com.app.bank_app.api.enums.Status;
import com.app.bank_app.api.models.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class DepositDto {
    private Integer id;
    private double amount;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Source source;
    private String accountName;

}
