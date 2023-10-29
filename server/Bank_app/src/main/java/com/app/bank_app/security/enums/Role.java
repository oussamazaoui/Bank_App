package com.app.bank_app.security.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    user(
            Set.of(
                    Permission.ACCOUNT_DELETE,
                    Permission.ACCOUNT_CREATE,
                    Permission.TRANSACTION_CREATE,
                    Permission.TRANSACTION_READ,
                    Permission.ACCOUNT_READ,
                    Permission.CUSTOMER_READ
            )
    ),
    admin(
            Set.of(
                    Permission.TRANSACTION_READ,
                    Permission.ACCOUNT_READ,
                    Permission.CUSTOMER_READ,
                    Permission.CUSTOMER_DELETE,
                    Permission.CUSTOMER_UPDATE

            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities=getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }

}
