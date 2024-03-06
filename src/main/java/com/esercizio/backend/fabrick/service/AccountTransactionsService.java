package com.esercizio.backend.fabrick.service;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.entity.AccountTransactionEntity;
import com.esercizio.backend.fabrick.mapper.AccountTransactionMapper;
import com.esercizio.backend.fabrick.model.api.AccountTransaction;
import com.esercizio.backend.fabrick.model.api.PlatformApiTransactionsApiResponse;
import com.esercizio.backend.fabrick.model.api.AccountTransactionsResponse;
import com.esercizio.backend.fabrick.service.clientRepository.AccountTransactionsRepositoryService;
import com.esercizio.backend.fabrick.service.clientRest.AccountTransactionRestClientRest;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountTransactionsService {

    @Autowired
    private AccountTransactionsRepositoryService accountTransactionsRepositoryService;

    @Autowired
    private AccountTransactionMapper accountTransactionMapper;

    @Autowired
    private AccountTransactionRestClientRest accountTransactionRestClientRest;

    public ResponseEntity<AccountTransactionsResponse> retrieveAccountTransactions(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {
        String idAccount = bankAccontParamInputBin.getIdAccount();
        List<AccountTransactionEntity> accountTransactions = accountTransactionsRepositoryService.retrieveAccountTransactionFromDB(bankAccontParamInputBin);

        if (accountTransactions.isEmpty()) {
            PlatformApiTransactionsApiResponse result = accountTransactionRestClientRest.executeApi(bankAccontParamInputBin);
            if (result.getPayload() != null) {
                accountTransactions = result.getPayload()
                        .getList().stream()
                        .map(accountTransaction -> accountTransactionMapper.fromAccountTransactionsResponseToAccountTransactionEntity(accountTransaction, idAccount))
                        .collect(Collectors.toList());

                accountTransactionsRepositoryService.saveAccountTransactionToDB(accountTransactions, idAccount);
            }

            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        } else {
            List<AccountTransaction> accountTransactionLists = accountTransactions.stream()
                    .map(accountTransactionEntity -> accountTransactionMapper.fromAccountTransactionEntityToAccountTransactionsResponse(accountTransactionEntity))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(prepareAccountTransactionsResponse(accountTransactionLists), HttpStatus.OK);
        }
    }

    private AccountTransactionsResponse prepareAccountTransactionsResponse(List<AccountTransaction> accountTransactionLists) {
        AccountTransactionsResponse accountTransactionsResponse = new AccountTransactionsResponse();
        accountTransactionsResponse.setList(accountTransactionLists);
        return accountTransactionsResponse;
    }

}
