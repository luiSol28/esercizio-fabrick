package com.esercizio.backend.fabrick.service;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.entity.RequestAccountTransactionEntity;
import com.esercizio.backend.fabrick.model.api.PlatformApiTransactionsApiResponse;
import com.esercizio.backend.fabrick.model.api.AccountTransactionsResponse;
import com.esercizio.backend.fabrick.service.clientRepository.AccountTransactionsRepositoryService;
import com.esercizio.backend.fabrick.service.clientRest.AccountTransactionRestClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class AccountTransactionsService {

    @Autowired
    private AccountTransactionsRepositoryService accountTransactionsRepositoryService;

    @Autowired
    private AccountTransactionRestClientService accountTransactionRestClientService;

    public ResponseEntity<AccountTransactionsResponse> retrieveAccountTransactions(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {

        String fromAccountingDate = bankAccontParamInputBin.getFromAccountingDate();
        List<RequestAccountTransactionEntity> accountTransactions = accountTransactionsRepositoryService.retrieveAccountTransactionFromDB(bankAccontParamInputBin);
        if (accountTransactions.isEmpty() ||
                (fromAccountingDate != null && fromAccountingDate.equals(LocalDate.now().toString()))) {
            PlatformApiTransactionsApiResponse result = accountTransactionRestClientService.executeApi(bankAccontParamInputBin);
            if (result.getPayload() != null) {
                accountTransactionsRepositoryService.saveAccountTransactionToDB(result, bankAccontParamInputBin);
            }
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(preparePlayload(accountTransactions.get(0)).getPayload(), HttpStatus.OK);
        }
    }

    private PlatformApiTransactionsApiResponse preparePlayload(RequestAccountTransactionEntity accountTransactions) throws IOException {
        PlatformApiTransactionsApiResponse result = covertObjectInJSON(accountTransactions.getContent());
        return result;
    }

    private PlatformApiTransactionsApiResponse covertObjectInJSON(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, PlatformApiTransactionsApiResponse.class);
    }
}
