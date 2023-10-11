package com.app.bank_app.classes.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepositry extends JpaRepository<Account,Integer> {
    Optional<Account> findAccountByAccountNumber(Integer accountNumber);
}
