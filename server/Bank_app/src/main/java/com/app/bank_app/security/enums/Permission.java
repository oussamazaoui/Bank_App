package com.app.bank_app.security.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ACCOUNT_READ("account:read"),
    ACCOUNT_DELETE("account:delete"),
    ACCOUNT_CREATE("account:create"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_READ_PROF("customer:readProfile"),
    CUSTOMER_READ_MY_ACCOUNT("account:readMyAccount"),
    CUSTOMER_UPDATE("customer:update"),
    CUSTOMER_CREATE("customer:create"),
    CUSTOMER_DELETE("customer:delete"),
     TRANSACTION_READ("transaction:read"),
    TRANSACTION_CREATE("transaction:create");

    @Getter
    private final String permission;


}
