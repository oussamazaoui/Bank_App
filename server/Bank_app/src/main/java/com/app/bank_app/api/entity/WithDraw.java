package com.app.bank_app.api.entity;

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
@DiscriminatorValue("withdraw")
public class WithDraw extends Transaction {
    @Column(unique = true)
    private String accountName;

}
