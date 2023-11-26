package com.app.bank_app.api.controllers;

import com.app.bank_app.api.entity.Payement;
import com.app.bank_app.api.services.PayementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/payement")
@RequiredArgsConstructor
public class PayementController {
    private final PayementService payementService;
    @GetMapping(path = "/{customer_id}")
    public List<Payement> getAllPayement(@PathVariable("customer_id") Integer id){
        return payementService.getAllPayements(id);
    }
}
