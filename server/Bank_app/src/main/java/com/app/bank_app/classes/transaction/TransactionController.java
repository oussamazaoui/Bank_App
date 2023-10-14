package com.app.bank_app.classes.transaction;

import com.app.bank_app.classes.response.RequestResponse;
import com.app.bank_app.classes.transaction.subClasses.deposite.DepositeService;
import com.app.bank_app.classes.transaction.subClasses.deposite.RequestDeposit;
import com.app.bank_app.classes.transaction.subClasses.payement.PayementService;
import com.app.bank_app.classes.transaction.subClasses.transfer.TransferService;
import com.app.bank_app.classes.transaction.subClasses.withdraw.WithDrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    @Autowired
    private DepositeService depositeService;
    @Autowired
    private TransferService transferService;
    @Autowired
    private PayementService payementService;
    @Autowired
    private WithDrawService withDrawService;

    @PostMapping(path = "/deposit")
    public ResponseEntity<RequestResponse> deposit(@RequestBody RequestDeposit request ){
       if(depositeService.deposit(request)){
           return ResponseEntity.ok(new RequestResponse("deposit successful")) ;
       }
       return ResponseEntity.badRequest().body(new RequestResponse("deposit failed"));
    }


}
