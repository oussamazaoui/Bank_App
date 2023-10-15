package com.app.bank_app.server.repositries;

import com.app.bank_app.server.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TransactionRepositry extends JpaRepository<Transaction,Integer> {
}
