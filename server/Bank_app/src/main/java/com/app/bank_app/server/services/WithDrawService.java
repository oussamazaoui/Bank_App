package com.app.bank_app.server.services;

import com.app.bank_app.server.models.Account;
import com.app.bank_app.server.repositries.AccountRepositry;
import com.app.bank_app.server.models.Customer;
import com.app.bank_app.server.repositries.CustomerRepositry;
import com.app.bank_app.server.enums.Status;
import com.app.bank_app.server.models.Transaction;
import com.app.bank_app.server.request.RequestWithDraw;
import com.app.bank_app.server.models.WithDraw;
import com.app.bank_app.server.repositries.WithDrawRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WithDrawService {
    @Autowired
    private WithDrawRepositry withDrawRepositry;
    @Autowired
    private AccountRepositry accountRepositry;
    @Autowired
    private CustomerRepositry customerRepositry;
    public boolean withDraw(RequestWithDraw request) {
        if(addTransaction(request)){
            Optional<Customer> customer=customerRepositry.findById(request.getCustomer_id());
            Optional<Account> account=accountRepositry.findAccountByCustomerAndAccountName(customer.get(),request.getAccountName());
            double amount=account.get().getBalance();
            account.get().setBalance(amount-request.getAmount());
            accountRepositry.save(account.get());
            return true;
        }
        return false;
    }
    public boolean addTransaction(RequestWithDraw request){
        if(requestIsValide(request)){
            if(isExist(request.getCustomer_id())){
                Optional<Customer> customer=customerRepositry.findById(request.getCustomer_id());
                Optional<Account> account=accountRepositry.findAccountByCustomerAndAccountName(customer.get(),request.getAccountName());
                if(account.get().getBalance()-request.getAmount()>=0) {
                    Transaction transaction = WithDraw
                            .builder()
                            .customer(customer.get())
                            .accountName(request.getAccountName())
                            .amount(request.getAmount())
                            .status(Status.success)
                            .build();
                    withDrawRepositry.save(transaction);
                    return true;
                }
                else {
                    Transaction transaction = WithDraw
                            .builder()
                            .customer(customer.get())
                            .accountName(request.getAccountName())
                            .amount(request.getAmount())
                            .status(Status.failed)
                            .build();
                    withDrawRepositry.save(transaction);
                    return false;
                }
            }
        }
        return false;
    }
    public boolean requestIsValide(RequestWithDraw request){
        return request.getAmount()!=null && request.getAccountName()!=null && request.getCustomer_id()!=null;
    }
    public boolean isExist(Integer id){
        if(customerRepositry.findById(id).isPresent()){
            return true;
        }
        return false;
    }
}
