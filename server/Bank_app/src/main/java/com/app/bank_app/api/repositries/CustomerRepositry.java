package com.app.bank_app.api.repositries;

import com.app.bank_app.api.models.Customer;
import com.app.bank_app.api.ressources.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepositry extends JpaRepository<Customer,Integer> {


    Optional<Customer> findByemail(String email);

}
