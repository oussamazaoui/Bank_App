package com.app.bank_app.api.repositries;

import com.app.bank_app.api.models.Deposite;
import com.app.bank_app.api.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepositry extends JpaRepository<Transfer,Integer> {
}