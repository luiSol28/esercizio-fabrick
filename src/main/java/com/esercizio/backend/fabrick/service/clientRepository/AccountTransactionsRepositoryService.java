package com.esercizio.backend.fabrick.service.clientRepository;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.entity.RequestAccountTransactionEntity;
import com.esercizio.backend.fabrick.mapper.AccountTransactionMapper;
import com.esercizio.backend.fabrick.model.api.PlatformApiTransactionsApiResponse;
import com.esercizio.backend.fabrick.repository.AccountTransactionsRepository;
import com.esercizio.backend.fabrick.specification.RequestAccountTransactionSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountTransactionsRepositoryService {

    Logger logger = LoggerFactory.getLogger(AccountTransactionsRepositoryService.class);

    @Autowired
    private AccountTransactionMapper accountTransactionMapper;

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    public List<RequestAccountTransactionEntity> retrieveAccountTransactionFromDB(BankAccontParamInputBin bankAccontParamInputBin) {
        String idAccount = bankAccontParamInputBin.getIdAccount();
        String toAccountingDate = bankAccontParamInputBin.getToAccountingDate();
        String fromAccountingDate = bankAccontParamInputBin.getFromAccountingDate();
        logger.info("RETRIEVE account transactions from DB for idAccount: {}, fromAccountingDate:  {}, toAccountingDate: {}", idAccount, fromAccountingDate, toAccountingDate);
        List<RequestAccountTransactionEntity> accountTransaction = accountTransactionsRepository.findAll(RequestAccountTransactionSpecification.retrieveRequestAccountTransactionByIdAccountAndtoAccountingDateAndFromAccountingDate(bankAccontParamInputBin));
        logger.info("RETRIEVED account transactions for idAccount: {},f romAccountingDate:  {}, toAccountingDate: {} from DB: {}", idAccount, fromAccountingDate, toAccountingDate, accountTransaction);
        List<RequestAccountTransactionEntity> accountTransaction1 = accountTransactionsRepository.findAll();
        return accountTransaction;
    }

    public RequestAccountTransactionEntity saveAccountTransactionToDB(PlatformApiTransactionsApiResponse result, BankAccontParamInputBin bankAccontParamInputBin) throws JsonProcessingException {
        String idAccount = bankAccontParamInputBin.getIdAccount();
        String toAccountingDate = bankAccontParamInputBin.getToAccountingDate();
        String fromAccountingDate = bankAccontParamInputBin.getFromAccountingDate();
        logger.info("SAVE account transactions to DB for idAccount: {}, fromAccountingDate:  {}, toAccountingDate: {}", idAccount, fromAccountingDate, toAccountingDate);
        RequestAccountTransactionEntity requestAccountTransactionEntity = prepareAccountTransactionsResponse(result, bankAccontParamInputBin);
        RequestAccountTransactionEntity accountTransactionSaved = accountTransactionsRepository.save(requestAccountTransactionEntity);
        logger.info("SAVED account transactions for idAccount: {}, fromAccountingDate:  {}, toAccountingDate: {} to DB: {}", idAccount, fromAccountingDate, toAccountingDate, accountTransactionSaved);
        return accountTransactionSaved;
    }

    private RequestAccountTransactionEntity prepareAccountTransactionsResponse(PlatformApiTransactionsApiResponse response, BankAccontParamInputBin bankAccontParamInputBin) throws JsonProcessingException {
        RequestAccountTransactionEntity requestAccountTransactionEntity = accountTransactionMapper.fromtoAccountTransactionEntity(bankAccontParamInputBin);
        requestAccountTransactionEntity.setContent(convertPlatformApiTransactionsApiResponsetoStringJson(response));
        return requestAccountTransactionEntity;
    }

    private String convertPlatformApiTransactionsApiResponsetoStringJson(PlatformApiTransactionsApiResponse response) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(response);
    }
}
