package com.esercizio.backend.fabrick.repository;

import com.esercizio.backend.fabrick.entity.AccountTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionsRepository extends JpaRepository<AccountTransactionEntity, String> {
}
