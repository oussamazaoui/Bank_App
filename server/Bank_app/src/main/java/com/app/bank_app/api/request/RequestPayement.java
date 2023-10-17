package com.app.bank_app.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPayement {
    private String accountName;
    private Double amount;
    private Integer customer_id;
    private String beneficiary;
    private Integer otherAccountNum;
    private String reference;
}
