package com.app.bank_app.api.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDeposit {
    private String accountName;
    private Double amount;
    private Integer customer_id;
}

