package com.app.bank_app.api.services;

import com.app.bank_app.api.models.Account;
import com.app.bank_app.api.repositries.AccountRepositry;
import com.app.bank_app.api.models.Customer;
import com.app.bank_app.api.repositries.CustomerRepositry;
import com.app.bank_app.api.enums.Source;
import com.app.bank_app.api.enums.Status;
import com.app.bank_app.api.models.Transaction;
import com.app.bank_app.api.models.Payement;
import com.app.bank_app.api.repositries.PayementRepositry;
import com.app.bank_app.api.request.RequestPayement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PayementService {
    @Autowired
    private AccountRepositry accountRepositry;
    @Autowired
    private CustomerRepositry customerRepositry;
    @Autowired
    private PayementRepositry payementRepositry;
    @Autowired
    private DepositeService depositeService;

    public Integer payement(RequestPayement request) {
        Transaction transaction= Payement.
                builder()
                .beneficiary(request.getBeneficiary())
                .amount(request.getAmount())
                .status(Status.success)
                .accountName(request.getAccountName())
                .accountNumber(request.getOtherAccountNum())
                .reference(request.getReference())
                .customer(customerRepositry.findById(request.getCustomer_id()).get())
                .source(Source.online)
                .build();
     if(isValid(request)){
         if(isOtherAccountExist(request)){
             if(withdraw(request.getCustomer_id(), request.getAmount(),request.getAccountName())){
                 if(deposit(request.getOtherAccountNum(), request.getAmount())){

                     payementRepositry.save(transaction);
                     return 4;
                 }
                 transaction.setStatus(Status.failed);
                 payementRepositry.save(transaction);
                 return 3;
             }
             transaction.setStatus(Status.failed);
             payementRepositry.save(transaction);

             return 2;
         }
         transaction.setStatus(Status.failed);
         payementRepositry.save(transaction);
         return 1;
     }
        transaction.setStatus(Status.failed);
        payementRepositry.save(transaction);
     return 0;
    }

    private boolean withdraw(Integer id,double amount,String accountName) {

        Optional<Customer> customer=customerRepositry.findById(id);
        Optional<Account> account=accountRepositry.findAccountByCustomerAndAccountName(customer.get(),accountName);
        double newAmount=account.get().getBalance()-amount;
        if(amount>=0){
            account.get().setBalance(newAmount);
            accountRepositry.save(account.get());
            return true;
        }
        return false;
    }
    private boolean deposit(Integer accountNum,double amount){
        Optional<Account> account=accountRepositry.findAccountByAccountNumber(accountNum);
        double newAmount=account.get().getBalance()+amount;
        if(account.isPresent()){
            account.get().setBalance(newAmount);
            accountRepositry.save(account.get());
            return true;
        }
        return false;

    }

    public boolean isOtherAccountExist(RequestPayement request){
        Optional<Account> account=accountRepositry.findAccountByAccountNumber(request.getOtherAccountNum());
        Optional<Customer> customer=customerRepositry.findById(request.getCustomer_id());
        Optional<Account> myAccount=accountRepositry.findAccountByCustomerAndAccountName(customer.get(), request.getAccountName());
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
