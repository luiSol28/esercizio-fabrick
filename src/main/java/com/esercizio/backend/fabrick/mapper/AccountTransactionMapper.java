package com.esercizio.backend.fabrick.mapper;

import com.esercizio.backend.fabrick.entity.AccountTransactionEntity;
import com.esercizio.backend.fabrick.model.api.AccountTransaction;
import com.esercizio.backend.fabrick.service.AccountTransactionsService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses= AccountTransactionsService.class)
public interface AccountTransactionMapper {

    AccountTransactionEntity fromAccountTransactionsResponseToAccountTransactionEntity(AccountTransaction accountTransaction, String idAccount);

    AccountTransaction fromAccountTransactionEntityToAccountTransactionsResponse(AccountTransactionEntity accountTransactionEntity);

}