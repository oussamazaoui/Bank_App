package com.app.bank_app.api.repositries;

import com.app.bank_app.api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepositry extends JpaRepository<Transaction,Integer> {
   List<Transaction> findAll();
}
