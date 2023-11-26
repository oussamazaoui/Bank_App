package com.app.bank_app.api.repositries;

import com.app.bank_app.api.entity.Account;
import com.app.bank_app.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepositry extends JpaRepository<Account,Integer> {
    Optional<Account> findAccountByAccountNumber(Integer accountNumber);

   Optional<Account> findAccountByCustomerAndAccountName(Customer customer,String accountName);
    Optional<Account> findByAccountName( String accountName);



}
