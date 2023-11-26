package com.app.bank_app.api.controllers;

import com.app.bank_app.api.entity.Deposit;
import com.app.bank_app.api.services.DepositeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/deposit")
@RequiredArgsConstructor
public class DepositController {
    private final DepositeService depositeService;
    @GetMapping(path = "/{customer_id}")
    public List<Deposit> getAllDeposit(@PathVariable("customer_id") Integer id){
        return depositeService.getAllDeposit(id);
    }
}
