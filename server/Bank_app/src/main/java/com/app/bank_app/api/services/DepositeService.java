package com.app.bank_app.api.services;

import com.app.bank_app.api.entity.Account;
import com.app.bank_app.api.entity.Customer;
import com.app.bank_app.api.enums.Status;
import com.app.bank_app.api.entity.Transaction;
import com.app.bank_app.api.entity.Deposit;
import com.app.bank_app.api.repositries.DepositRepositry;
import com.app.bank_app.api.request.RequestDeposit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepositeService {
   private final CustomerService customerService;
   private final AccountService accountService;
   private final DepositRepositry depositRepositry;
   private final TransactionService transactionService;

   public List<Deposit> getAllDeposit(Integer id){
       return depositRepositry.findAllByCustomer_Id(id);
   }

    public boolean deposit(RequestDeposit request){
        if(addTransaction(request)){
            Optional<Customer> customer=customerService.getCustomer(request.getCustomer_id());
            Optional<Account> account=accountService.getAccountByCustomerAndName(customer.get(),request.getAccountName());
            double amount=account.get().getBalance();
            account.get().setBalance(amount+ request.getAmount());
            accountService.addNewAccount(account.get());
            return true;
        }
        return false;
    }
    public boolean addTransaction(RequestDeposit request){
        if(requestIsValide(request)){
            if(isExist(request.getCustomer_id())){
                Optional<Customer> customer=customerService.getCustomer(request.getCustomer_id());
                if(request.getAmount()>0) {
                    Transaction transaction = Deposit
                            .builder()
                            .customer(customer.get())
                            .accountName(request.getAccountName())
                            .amount(request.getAmount())
                            .status(Status.success)
                            .build();
                    transactionService.newTransaction(transaction);
                    return true;
                }
                else {
                    Transaction transaction = Deposit
                            .builder()
                            .customer(customer.get())
                            .accountName(request.getAccountName())
                            .amount(request.getAmount())
                            .status(Status.failed)
                            .build();
                    transactionService.newTransaction(transaction);
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
        if(customerService.getCustomer(id).isPresent()){
            return true;
        }
        return false;
    }




}
