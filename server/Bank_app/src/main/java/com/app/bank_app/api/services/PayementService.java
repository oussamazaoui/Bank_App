package com.app.bank_app.api.services;

import com.app.bank_app.api.entity.Account;
import com.app.bank_app.api.entity.Customer;
import com.app.bank_app.api.enums.Source;
import com.app.bank_app.api.enums.Status;
import com.app.bank_app.api.entity.Transaction;
import com.app.bank_app.api.entity.Payement;
import com.app.bank_app.api.repositries.PayementRepositry;
import com.app.bank_app.api.request.RequestPayement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PayementService {
    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final PayementRepositry payementRepositry;

    public List<Payement> getAllPayements(Integer id){
        return payementRepositry.findAllByCustomer_Id(id);
    }

    public Integer payement(RequestPayement request) {
        Transaction transaction= Payement.
                builder()
                .beneficiary(request.getBeneficiary())
                .amount(request.getAmount())
                .status(Status.success)
                .accountName(request.getAccountName())
                .accountNumber(request.getOtherAccountNum())
                .reference(request.getReference())
                .customer(customerService.getCustomer(request.getCustomer_id()).get())
                .source(Source.online)
                .build();
     if(isValid(request)){
         if(isOtherAccountExist(request)){
             if(withdraw(request.getCustomer_id(), request.getAmount(),request.getAccountName())){
                 if(deposit(request.getOtherAccountNum(), request.getAmount())){

                     transactionService.newTransaction(transaction);
                     return 4;
                 }
                 transaction.setStatus(Status.failed);
                 transactionService.newTransaction(transaction);
                 return 3;
             }
             transaction.setStatus(Status.failed);
             transactionService.newTransaction(transaction);

             return 2;
         }
         transaction.setStatus(Status.failed);
         transactionService.newTransaction(transaction);
         return 1;
     }
        transaction.setStatus(Status.failed);
        transactionService.newTransaction(transaction);
     return 0;
    }

    private boolean withdraw(Integer id,double amount,String accountName) {

        Optional<Customer> customer=customerService.getCustomer(id);
        Optional<Account> account=accountService.getAccountByCustomerAndName(customer.get(),accountName);
        double newAmount=account.get().getBalance()-amount;
        if(amount>=0){
            account.get().setBalance(newAmount);
            accountService.addNewAccount(account.get());
            return true;
        }
        return false;
    }
    private boolean deposit(Integer accountNum,double amount){
        Optional<Account> account=accountService.getAccountByNumber(accountNum);
        double newAmount=account.get().getBalance()+amount;
        if(account.isPresent()){
            account.get().setBalance(newAmount);
            accountService.addNewAccount(account.get());
            return true;
        }
        return false;

    }

    public boolean isOtherAccountExist(RequestPayement request){
        Optional<Account> account=accountService.getAccountByNumber(request.getOtherAccountNum());
        Optional<Customer> customer=customerService.getCustomer(request.getCustomer_id());
        Optional<Account> myAccount=accountService.getAccountByCustomerAndName(customer.get(), request.getAccountName());
        if(account.isPresent() && account.get().getAccountNumber()!=myAccount.get().getAccountNumber()){
            return true;
        }
        return false;
    }
    public boolean isValid(RequestPayement request){
        return request.getAmount()!=null && request.getOtherAccountNum()!=null
                && request.getBeneficiary()!=null && request.getAccountName()!=null
                && request.getCustomer_id()!=null && request.getReference()!=null;
    }
}
