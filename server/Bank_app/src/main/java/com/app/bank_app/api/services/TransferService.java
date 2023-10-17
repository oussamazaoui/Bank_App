package com.app.bank_app.api.services;

import com.app.bank_app.api.models.Account;
import com.app.bank_app.api.models.Customer;
import com.app.bank_app.api.enums.Status;
import com.app.bank_app.api.models.Transaction;
import com.app.bank_app.api.request.RequestTransfer;
import com.app.bank_app.api.models.Transfer;
import com.app.bank_app.api.repositries.TransferRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepositry transferRepositry;
    private final AccountService accountService;
    private final CustomerService customerService;



    public Integer transfer(RequestTransfer request){
        if(requestIsValid(request)){
            Optional<Account> account=accountService.getAccountBynameAndCustomer(request.getAccountName(), request.getCustomer_id());
            Optional<Account> otherAccount=accountService.getAccountBynameAndCustomer(request.getOtherAccountName(), request.getCustomer_id());
            Optional<Customer> customer=customerService.getCustomer(request.getCustomer_id());
            double newAmount=account.get().getBalance()- request.getAmount();
            double newAmount1=otherAccount.get().getBalance()+ request.getAmount();
            if(newAmount>=0) {
                account.get().setBalance(newAmount);
                accountService.Update(account.get());
                otherAccount.get().setBalance(newAmount1);
                accountService.Update(otherAccount.get());
                Transaction transaction= Transfer
                        .builder()
                        .customer(customer.get())
                        .accountName(request.getAccountName())
                        .otherAccountName(request.getOtherAccountName())
                        .amount(request.getAmount())
                        .status(Status.success)
                        .build();
                transferRepositry.save(transaction);
                return 2;
            }
            Transaction transaction=Transfer
                    .builder()
                    .customer(customer.get())
                    .accountName(request.getAccountName())
                    .otherAccountName(request.getOtherAccountName())
                    .amount(request.getAmount())
                    .status(Status.failed)
                    .build();
            transferRepositry.save(transaction);
            return 1;
        }
        return 0;
    }
    public boolean requestIsValid(RequestTransfer request){
        return request.getAccountName()!=null && request.getOtherAccountName()!=null&& request.getCustomer_id()!=null && request.getAmount()>0;
    }
}
