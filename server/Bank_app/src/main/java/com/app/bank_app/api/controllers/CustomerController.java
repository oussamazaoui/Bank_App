package com.app.bank_app.api.controllers;

import com.app.bank_app.api.models.Customer;
import com.app.bank_app.api.ressources.CustomerDto;
import com.app.bank_app.api.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/customer")
@RequiredArgsConstructor
@PreAuthorize("hasRole('admin')")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    @PreAuthorize("hasAuthority('customer:read')")
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
