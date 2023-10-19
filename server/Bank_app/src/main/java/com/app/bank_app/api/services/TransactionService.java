package com.app.bank_app.api.services;

import com.app.bank_app.api.models.Transaction;
import com.app.bank_app.api.repositries.TransactionRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepositry transactionRepositry;
    public List<Transaction> getAllTransactions()
    {
        return transactionRepositry.findAll();
    }
    public void newTransaction(Transaction transaction){
        transactionRepositry.save(transaction);
    }

}
