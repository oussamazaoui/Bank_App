package com.app.bank_app.api.repositries;

import com.app.bank_app.api.models.Deposite;
import com.app.bank_app.api.models.WithDraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithDrawRepositry extends JpaRepository<WithDraw,Integer> {
}
