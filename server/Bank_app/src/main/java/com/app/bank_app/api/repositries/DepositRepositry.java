package com.app.bank_app.api.repositries;

import com.app.bank_app.api.models.Deposit;
import com.app.bank_app.api.ressources.DepositDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepositry extends JpaRepository<Deposit,Integer> {
    List<Deposit> findAllByCustomer_Id(Integer id);
}
