package com.app.bank_app.api.repositries;

import com.app.bank_app.api.entity.Payement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayementRepositry extends JpaRepository<Payement,Integer> {
    List<Payement> findAllByCustomer_Id(Integer id);
}
