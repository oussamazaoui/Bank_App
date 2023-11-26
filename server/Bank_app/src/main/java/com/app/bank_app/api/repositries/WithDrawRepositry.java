package com.app.bank_app.api.repositries;

import com.app.bank_app.api.entity.WithDraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithDrawRepositry extends JpaRepository<WithDraw,Integer> {
    List<WithDraw> findAllByCustomer_Id(Integer id);
}
