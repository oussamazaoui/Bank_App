package com.app.bank_app.server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer {
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
    @Transient
    private Integer age;
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;
    @OneToMany(mappedBy = "customer")
    private List<Transaction> transactions;

//    public Customer(Integer id, String firstName, String lastName, String phoneNumber, LocalDate birthday, String id_Card) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneNumber = phoneNumber;
//        this.birthday = birthday;
//        this.id_Card = id_Card;
//
//    }

    public Integer getAge() {
        return Period.between(this.birthday,LocalDate.now()).getYears();
    }
}
