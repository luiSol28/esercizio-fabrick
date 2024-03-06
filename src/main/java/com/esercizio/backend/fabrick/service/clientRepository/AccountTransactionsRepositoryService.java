package com.esercizio.backend.fabrick.service.clientRepository;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.entity.AccountTransactionEntity;
import com.esercizio.backend.fabrick.repository.AccountTransactionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class AccountTransactionsRepositoryService {

    Logger logger = LoggerFactory.getLogger(AccountTransactionsRepositoryService.class);

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    public List<AccountTransactionEntity> retrieveAccountTransactionFromDB(BankAccontParamInputBin bankAccontParamInputBin) {
        String idAccount = bankAccontParamInputBin.getIdAccount();
        logger.info("RETRIEVE account transactions from DB for idAccount {}", idAccount);
        List<AccountTransactionEntity> accountTransaction = accountTransactionsRepository.findAll();
        logger.info("RETRIEVED account transactions for idAccount {} from DB: {}", idAccount, accountTransaction);
        return accountTransaction;
    }

    public List<AccountTransactionEntity> saveAccountTransactionToDB(List<AccountTransactionEntity> accountTransactionEntityList, String idAccount) {
        logger.info("SAVE account transactions to DB for idAccount {}", idAccount);
        setIdAccountToAccountTransactionEntityList(accountTransactionEntityList, idAccount);
        List<AccountTransactionEntity> accountTransaction = accountTransactionsRepository.saveAll(accountTransactionEntityList);
        logger.info("SAVE account transactions for idAccount {} to DB: {}", idAccount, accountTransaction);
        return accountTransaction;
    }

    private void setIdAccountToAccountTransactionEntityList(List<AccountTransactionEntity> accountTransactions, String idAccount) {
        accountTransactions.forEach(accountTransactionEntity -> accountTransactionEntity.setIdAccount(new BigInteger(idAccount)));
    }
}
