package com.app.bank_app.classes.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface TransactionRepositry extends JpaRepository<Transaction,Integer> {
}
