package com.app.bank_app.server.controllers;

import com.app.bank_app.server.models.Account;
import com.app.bank_app.server.models.Customer;
import com.app.bank_app.server.request.AccountPostRequest;
import com.app.bank_app.server.request.AccountWithCustomer;
import com.app.bank_app.server.ressources.AccountRessource;
import com.app.bank_app.server.services.CustomerService;
import com.app.bank_app.server.response.RequestResponse;
import com.app.bank_app.server.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final CustomerService customerService;

    @GetMapping
    public List<AccountRessource> getAccount(){
        List<AccountRessource> listeAccounts=new ArrayList<>();

         for(Account account:accountService.getAccount()){
             AccountRessource accountRessource=AccountRessource
                     .builder()
                     .accountNumber(account.getAccountNumber())
                     .balance(account.getBalance())
                     .customer_id(account.getCustomer().getId())
                     .openingDate(account.getOpeningDate())
                     .id(account.getId())
                     .isActive(account.isActive())
                     .accountType(account.getAccountType())
                     .openingDate(account.getOpeningDate())
                     .accountName(account.getAccountName())
                     .build();
             listeAccounts.add(accountRessource);
         }
         return listeAccounts;
    }
    @GetMapping(path = "/{account_id}")
    public AccountRessource getAccount(@PathVariable("account_id") Integer id) {
        Optional<Account> account = accountService.getAccount(id);

            AccountRessource accountRessource=AccountRessource
                    .builder()
                    .accountNumber(account.get().getAccountNumber())
                    .balance(account.get().getBalance())
                    .customer_id(account.get().getCustomer().getId())
                    .openingDate(account.get().getOpeningDate())
                    .id(account.get().getId())
                    .isActive(account.get().isActive())
                    .accountType(account.get().getAccountType())
                    .openingDate(account.get().getOpeningDate())
                    .accountName(account.get().getAccountName())
                    .build();
            return accountRessource;

    }
    @PostMapping
    public ResponseEntity<RequestResponse> addNewAccount(@RequestBody AccountPostRequest request) {
        if(requestIsValid(request)) {
            Optional<Customer> customer = customerService.getCustomer(request.getCustomer_id());
            if (customer.isPresent()) {
                Account account = Account
                        .builder()
                        .accountName(request.getAccountName())
                        .accountType(request.getAccountType())
                        .customer(customer.get())
                        .isActive(true)
                        .build();

                return ResponseEntity.ok(accountService.addNewAccount(account));
            } else {
                return ResponseEntity.badRequest().body(new RequestResponse("Customer not found "));
            }
        }
        return ResponseEntity.badRequest().body(new RequestResponse("request is not valid"));

    }
    @DeleteMapping(path = "/{account_id}")
    public ResponseEntity<RequestResponse> deleteAccount(@PathVariable("account_id") Integer id){

        return ResponseEntity.ok(accountService.deleteAccount(id));
    }

    @GetMapping(path = "/customer/{account_id}")
    public AccountRessource getAccountWithCustomer(@PathVariable("account_id") Integer id){
        Optional<Account> account=accountService.getAccount(id);
        if(account.isPresent()){
            Integer customer_id=account.get().getCustomer().getId();
            Optional<Customer> customer=customerService.getCustomer(customer_id);
            AccountWithCustomer accountWithCustomer=new AccountWithCustomer();
            accountWithCustomer.setAccountName(account.get().getAccountName());
            accountWithCustomer.setAccountType(account.get().getAccountType());
            accountWithCustomer.setAccountNumber(account.get().getAccountNumber());
            accountWithCustomer.setFirstName(customer.get().getFirstName());
            accountWithCustomer.setId_Card(customer.get().getId_Card());
            accountWithCustomer.setLastName(customer.get().getLastName());
            accountWithCustomer.setPhoneNumber(customer.get().getPhoneNumber());
            accountWithCustomer.setBalance(accountWithCustomer.getBalance());
            accountWithCustomer.setId(customer.get().getId());
            accountWithCustomer.setCustomer_id(customer.get().getId());
            accountWithCustomer.setAge(customer.get().getAge());
            accountWithCustomer.setOpeningDate(account.get().getOpeningDate());
            accountWithCustomer.setActive(account.get().isActive());

            return accountWithCustomer;
        }
        throw new IllegalStateException("the account is not find");
    }

    public boolean requestIsValid(AccountPostRequest request){
        return request.getCustomer_id()!=null && request.getAccountName()!=null && request.getAccountType()!=null;
    }

}
