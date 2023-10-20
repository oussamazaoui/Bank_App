package com.app.bank_app.api.controllers;

import com.app.bank_app.api.models.Deposite;
import com.app.bank_app.api.services.DepositeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/deposit")
@RequiredArgsConstructor
public class DepositController {
    private final DepositeService depositeService;
    public List<Deposite> getAllDeposit(){
        return depositeService.getAllDeposit();
    }
}
