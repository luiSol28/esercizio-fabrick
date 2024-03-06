package com.esercizio.backend.fabrick.repository;

import com.esercizio.backend.fabrick.entity.RequestAccountTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionsRepository extends JpaRepository<RequestAccountTransactionEntity, String>, JpaSpecificationExecutor<RequestAccountTransactionEntity> {
}
