package com.app.bank_app.classes.transaction.subClasses.transfer;

import com.app.bank_app.classes.transaction.TransactionRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface TransferRepositry extends TransactionRepositry {
}
