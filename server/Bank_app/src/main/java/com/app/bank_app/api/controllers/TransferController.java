package com.app.bank_app.api.controllers;

import com.app.bank_app.api.models.Transfer;
import com.app.bank_app.api.services.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;
    @GetMapping
    public List<Transfer> getAllTransfer(){
        return transferService.getAllTransfer();
    }
}
