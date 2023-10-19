package com.app.bank_app.api.services;

import com.app.bank_app.api.models.Account;
import com.app.bank_app.api.repositries.AccountRepositry;
import com.app.bank_app.api.models.Customer;
import com.app.bank_app.api.repositries.CustomerRepositry;
import com.app.bank_app.api.response.RequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepositry accountRepositry;
    private final CustomerRepositry customerRepositry;

    public List<Account> getAccounts(){
        return accountRepositry.findAll();

    }
    public Optional<Account> getAccount(Integer id){
        return accountRepositry.findById(id);
    }
    public Optional<Account> getAccountBynameAndCustomer(String name, Integer customer_id){
        Optional<Customer> customer1=customerRepositry.findById(customer_id);
        return accountRepositry.findAccountByCustomerAndAccountName(customer1.get(),name);
    }
    public RequestResponse addNewAccount(Account account) {
        try {

            accountRepositry.save(account);
            return new RequestResponse("Account added successfully");
        } catch (Exception e) {
            // Handle the exception (e.g., log the error)
            return new RequestResponse("Failed to add the account");
        }
    }
    public RequestResponse deleteAccount(Integer id) {

        try {
            accountRepositry.deleteById(id);
            return new RequestResponse("Account deleted successfully");
        } catch (Exception e) {
            // Handle the exception (e.g., log the error)
            return new RequestResponse("Failed to delete the account");
        }
    }
    public Optional<Account> getAccountByNumber(Integer accountNumber){
        return accountRepositry.findAccountByAccountNumber(accountNumber);

    }
    public void Update(Account account){
        accountRepositry.save(account);
    }
    public Optional<Account> getAccountByCustomerAndName(Customer customer,String name){
        return accountRepositry.findAccountByCustomerAndAccountName(customer,name);
    }
}
