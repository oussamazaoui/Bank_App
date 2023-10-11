package com.app.bank_app.classes.account;

import com.app.bank_app.classes.response.RequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepositry accountRepositry;

    public List<Account> getAccount(){
        return accountRepositry.findAll();
    }
    public Optional<Account> getAccount(Integer id){
        return accountRepositry.findById(id);
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
}
