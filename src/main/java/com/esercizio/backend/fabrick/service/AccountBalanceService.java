package com.esercizio.backend.fabrick.service;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.model.api.AccountBalance;
import com.esercizio.backend.fabrick.model.api.PlatformApiAccountBalanceApiResponse;
import com.esercizio.backend.fabrick.service.clientRest.AccountBalanceRestClientService;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccountBalanceService {

    Logger logger = LoggerFactory.getLogger(AccountBalanceService.class);

    @Autowired
    private AccountBalanceRestClientService accountBalanceRestClientService;

    public ResponseEntity<AccountBalance> retrieveBalance(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {

        PlatformApiAccountBalanceApiResponse result = accountBalanceRestClientService.executeApi(bankAccontParamInputBin);
        return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
    }




}
