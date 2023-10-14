package com.app.bank_app.classes.transaction.subClasses.payement;

import com.app.bank_app.classes.transaction.TransactionRepositry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayementRepositry extends TransactionRepositry {
}
