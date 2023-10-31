package com.app.bank_app.api.controllers;

import com.app.bank_app.api.models.Transaction;
import com.app.bank_app.api.repositries.TransactionRepositry;
import com.app.bank_app.api.response.RequestResponse;
import com.app.bank_app.api.services.*;
import com.app.bank_app.api.request.RequestDeposit;
import com.app.bank_app.api.request.RequestPayement;
import com.app.bank_app.api.request.RequestTransfer;
import com.app.bank_app.api.request.RequestWithDraw;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/transaction")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('user','admin')")
public class TransactionController {
    private final DepositeService depositeService;

    private final TransferService transferService;

    private final PayementService payementService;

    private final WithDrawService withDrawService;

    private final TransactionService transactionService;

    @GetMapping
    @PreAuthorize("hasAuthority('TRANSACTION_READ')")
    public List<Transaction> getTransactions(){
        return transactionService.getAllTransactions();
    }

    @PostMapping(path = "/deposit")
    @PreAuthorize("hasAuthority('TRANSACTION_CREATE')")
    public ResponseEntity<RequestResponse> deposit(@RequestBody RequestDeposit request ){
       if(depositeService.deposit(request)){
           return ResponseEntity.ok(new RequestResponse(" successful deposit "));
       }
       return ResponseEntity.badRequest().body(new RequestResponse(" failed deposit"));
    }
    @PostMapping(path="/withdraw")
    @PreAuthorize("hasAuthority('TRANSACTION_CREATE')")
    public ResponseEntity<RequestResponse> withDraw(@RequestBody RequestWithDraw request){
        if(withDrawService.withDraw(request)){
            return ResponseEntity.ok(new RequestResponse(" successful withdraw")) ;
        }
        return ResponseEntity.badRequest().body(new RequestResponse(" failed withdraw"));
    }
    @PostMapping(path = "/payement")
    @PreAuthorize("hasAuthority('TRANSACTION_CREATE')")
    public ResponseEntity<RequestResponse> payement(@RequestBody RequestPayement request) {
       Integer result=payementService.payement(request);
        if (result==4) {
            return ResponseEntity.ok(new RequestResponse("successful payement "));
        } else if (result==3) {
            return ResponseEntity.badRequest().body(new RequestResponse(" the deposit is impossible"));
        } else if (result==2) {
            return ResponseEntity.badRequest().body(new RequestResponse("the withdraw is impossible"));
        } else if (result==1) {
            return ResponseEntity.badRequest().body(new RequestResponse(" the occount does not exist"));
        }
        else{
            return ResponseEntity.badRequest().body(new RequestResponse(" error in request"));
        }


    }
    @PostMapping(path = "/transfer")
    @PreAuthorize("hasAuthority('TRANSACTION_CREATE')")
    public ResponseEntity<RequestResponse> transfer(@RequestBody RequestTransfer request){
        Integer result=transferService.transfer(request);
        if(result==2){
            return ResponseEntity.ok(new RequestResponse("successful transfer "));
        } else if (result==1) {
            return ResponseEntity.badRequest().body(new RequestResponse(" error in the amount"));
        }
        return ResponseEntity.badRequest().body(new RequestResponse(" error in request"));
    }


}
