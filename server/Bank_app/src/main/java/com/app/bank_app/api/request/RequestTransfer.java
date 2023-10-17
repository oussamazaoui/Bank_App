package com.app.bank_app.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestTransfer {
    private String accountName;
    private String otherAccountName;
    private Integer customer_id;
    private Double amount;
}
