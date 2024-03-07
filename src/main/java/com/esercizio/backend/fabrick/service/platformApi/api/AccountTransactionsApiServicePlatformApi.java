package com.esercizio.backend.fabrick.service.platformApi.api;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.entity.RequestAccountTransactionEntity;
import com.esercizio.backend.fabrick.mapper.AccountTransactionMapper;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiTransactionsApiResponse;
import com.esercizio.backend.fabrick.model.api.response.AccountTransactionsResponse;
import com.esercizio.backend.fabrick.model.platformApi.StatusPlatformApiResponseEnum;
import com.esercizio.backend.fabrick.repository.AccountTransactionsRepositoryService;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.AccountTransactionRestClientService;
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
public class AccountTransactionsApiServicePlatformApi implements PlatformApiRestApi<ResponseEntity<AccountTransactionsResponse>, BankAccontParamInputBin> {

    @Autowired
    private AccountTransactionsRepositoryService accountTransactionsRepositoryService;

    @Autowired
    private AccountTransactionRestClientService accountTransactionRestClientService;

    @Autowired
    private AccountTransactionMapper accountTransactionMapper;

    public ResponseEntity<AccountTransactionsResponse> executeApi(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {
        String fromAccountingDate = bankAccontParamInputBin.getFromAccountingDate();
        List<RequestAccountTransactionEntity> accountTransactions = accountTransactionsRepositoryService.retrieveAccountTransactionFromDB(bankAccontParamInputBin);
        if (accountTransactions.isEmpty() ||
                (fromAccountingDate != null && fromAccountingDate.equals(LocalDate.now().toString()))) {
            PlatformApiTransactionsApiResponse result = accountTransactionRestClientService.callApiRest(bankAccontParamInputBin);
            if (result.getPayload() != null && StatusPlatformApiResponseEnum.OK.equals(result.getStatus())) {
                accountTransactionsRepositoryService.saveAccountTransactionToDB(result, bankAccontParamInputBin);
            }
            return new ResponseEntity<>(accountTransactionMapper.converterToAccountTransactionsResponse(result), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    accountTransactionMapper.converterToAccountTransactionsResponse(preparePlayload(accountTransactions.get(0)))
                    , HttpStatus.OK);
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
