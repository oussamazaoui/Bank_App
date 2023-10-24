package com.app.bank_app.api.services;

import com.app.bank_app.api.models.Customer;
import com.app.bank_app.api.repositries.CustomerRepositry;
import com.app.bank_app.api.ressources.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepositry customerRepositry;
    public List<Customer> allCustomers(){
        return customerRepositry.findAll();
    }
    public Optional<Customer> getCustomer(Integer id){
         return customerRepositry.findById(id);
    }
    public void newCustomer(Customer customer){
         customerRepositry.save(customer);
    }
    public void delete(Integer id){
        customerRepositry.findById(id).orElseThrow(()->new IllegalStateException("the customer not found with "+id));
        customerRepositry.deleteById(id);
    }
    public Optional<Customer> getCustomerByEmail(String email){
        return customerRepositry.findByemail(email);
    }

}
