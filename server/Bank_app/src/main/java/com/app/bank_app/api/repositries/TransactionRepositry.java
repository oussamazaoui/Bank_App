package com.app.bank_app.api.repositries;

import com.app.bank_app.api.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TransactionRepositry extends JpaRepository<Transaction,Integer> {
}
