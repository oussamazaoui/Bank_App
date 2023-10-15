package com.app.bank_app.server.repositries;

import com.app.bank_app.server.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositry extends JpaRepository<Customer,Integer> {


}
