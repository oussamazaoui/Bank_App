package com.app.bank_app.api.repositries;

import com.app.bank_app.api.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepositry extends JpaRepository<Transfer,Integer> {
    List<Transfer> findAllByCustomer_Id(Integer id);
}
