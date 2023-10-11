package com.app.bank_app.classes.customer;

import com.app.bank_app.classes.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CustomerRepositry extends JpaRepository<Customer,Integer> {


}
