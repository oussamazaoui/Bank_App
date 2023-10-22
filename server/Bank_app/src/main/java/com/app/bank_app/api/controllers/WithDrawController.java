package com.app.bank_app.api.controllers;

import com.app.bank_app.api.models.WithDraw;
import com.app.bank_app.api.services.WithDrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/withdraw")
@RequiredArgsConstructor
public class WithDrawController {
    private final WithDrawService withDrawService;
    @GetMapping(path = "/{customer_id}")
    public List<WithDraw> getAllWithDraw(@PathVariable("customer_id") Integer id){
        return withDrawService.getAllWithDraw(id);
    }
}
