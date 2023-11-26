package com.app.bank_app.api.entity;

import com.app.bank_app.security.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String phoneNumber;
    private LocalDate birthday;
    @Column(unique = true)
    private String id_Card;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Transient
    private Integer age;
    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Account> accounts;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Transaction> transactions;



    public Integer getAge() {
        return Period.between(this.birthday, LocalDate.now()).getYears();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


