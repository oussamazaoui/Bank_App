package com.app.bank_app.classes.transaction.subClasses.deposite;

import com.app.bank_app.classes.account.Account;
import com.app.bank_app.classes.account.AccountRepositry;
import com.app.bank_app.classes.account.AccountService;
import com.app.bank_app.classes.customer.Customer;
import com.app.bank_app.classes.customer.CustomerRepositry;
import com.app.bank_app.classes.response.RequestResponse;
import com.app.bank_app.classes.transaction.Status;
import com.app.bank_app.classes.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepositeService {
    @Autowired
    private DespositeRepositry despositeRepositry;
    @Autowired
    private AccountRepositry accountRepositry;
    @Autowired
    private CustomerRepositry customerRepositry;

    public boolean deposit(RequestDeposit request){
        if(addTransaction(request)){
            Optional<Customer> customer=customerRepositry.findById(request.getCustomer_id());
            Optional<Account> account=accountRepositry.findAccountByCustomerAndAccountName(customer.get(),request.getAccountName());
            double amount=account.get().getBalance();
            account.get().setBalance(amount+ request.getAmount());
            accountRepositry.save(account.get());
            return true;
        }
        return false;
    }
    public boolean addTransaction(RequestDeposit request){
        if(requestIsValide(request)){
            if(isExist(request.getCustomer_id())){
                Optional<Customer> customer=customerRepositry.findById(request.getCustomer_id());
                if(request.getAmount()>0) {
                    Transaction transaction = Deposite
                            .builder()
                            .customer(customer.get())
                            .accountName(request.getAccountName())
                            .amount(request.getAmount())
                            .status(Status.success)
                            .build();
                    despositeRepositry.save(transaction);
                    return true;
                }
                else {
                    Transaction transaction = Deposite
                            .builder()
                            .customer(customer.get())
                            .accountName(request.getAccountName())
                            .amount(request.getAmount())
                            .status(Status.failed)
                            .build();
                    despositeRepositry.save(transaction);
                    return false;
                }
            }
        }
        return false;
    }
    public boolean requestIsValide(RequestDeposit request){
        return request.getAmount()!=null && request.getAccountName()!=null && request.getCustomer_id()!=null;
    }
    public boolean isExist(Integer id){
        if(customerRepositry.findById(id).isPresent()){
            return true;
        }
        return false;
    }




}
