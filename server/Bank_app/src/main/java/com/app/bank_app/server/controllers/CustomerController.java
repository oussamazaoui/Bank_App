package com.app.bank_app.server.controllers;

import com.app.bank_app.server.models.Customer;
import com.app.bank_app.server.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping
    public List<Customer> getCustomer(){
        return customerService.allCustomers();

    }
    @GetMapping(path = "/{customer_id}")
    public Optional<Customer> getCustomer(@PathVariable("customer_id") Integer id){
        return customerService.getCustomer(id);
    }
    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer){
        customerService.newCustomer(customer);
    }
    @DeleteMapping(path = "/{customer_id}")
    public void deleteCustomer(@PathVariable("customer_id") Integer id){
        customerService.delete(id);
    }
}
