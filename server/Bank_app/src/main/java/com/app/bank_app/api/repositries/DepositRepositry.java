package com.app.bank_app.api.repositries;

import com.app.bank_app.api.models.Deposite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepositry extends JpaRepository<Deposite,Integer> {
}
