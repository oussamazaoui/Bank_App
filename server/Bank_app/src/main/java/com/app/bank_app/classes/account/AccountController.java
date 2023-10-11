package com.app.bank_app.classes.account;

import com.app.bank_app.classes.response.RequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public List<Account> getAccount(){
        return accountService.getAccount();
    }
    @GetMapping(path = "/{account_id}")
    public ResponseEntity<?> getAccount(@PathVariable("account_id") Integer id) {
        Optional<Account> account = accountService.getAccount(id);
        if (account.isPresent()) {
            return ResponseEntity.ok(account.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<RequestResponse> addNewAccount(@RequestBody AccountPostRequest request) {
            Account account = Account
                    .builder()
                    .accountName(request.getAccountName())
                    .accountType(request.getAccountType())
                    .isActive(true)
                    .build();

            return ResponseEntity.ok(accountService.addNewAccount(account));

    }
    @DeleteMapping(path = "/{account_id}")
    public ResponseEntity<RequestResponse> deleteAccount(@PathVariable("account_id") Integer id){

        return ResponseEntity.ok(accountService.deleteAccount(id));
    }
    @PutMapping(path = "/{account_id}")
    public ResponseEntity<RequestResponse> deposit(
            @RequestBody DepositRequest request,
            @PathVariable("account_id") Integer id) {
        Optional<Account> account = accountService.getAccount(id);

        if (account.isPresent() && request.getBalance() > 0) {
            double newBalance = account.get().getBalance() + request.getBalance();
            account.get().setBalance(newBalance);
            accountService.addNewAccount(account.get()); // Save the updated account
            return ResponseEntity.ok(new RequestResponse("Successful deposit operation"));
        }

        return ResponseEntity.badRequest().body(new RequestResponse("Invalid deposit operation"));
    }

}
