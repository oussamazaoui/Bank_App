package com.app.bank_app.api.repositries;

import com.app.bank_app.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepositry extends JpaRepository<Customer,Integer> {


    Optional<Customer> findByEmail(String email);

}
