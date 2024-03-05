package com.esercizio.backend.fabrick.service;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.model.api.AccountBalance;
import com.esercizio.backend.fabrick.model.api.PlatformApiAccountBalanceApiResponse;
import com.esercizio.backend.fabrick.service.clientRest.AccountBalanceRestClientRest;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BalanceService {

    Logger logger = LoggerFactory.getLogger(BalanceService.class);

    @Autowired
    private AccountBalanceRestClientRest accountBalanceRestClientRest;

    public ResponseEntity<AccountBalance> retrieveBalance(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {

        PlatformApiAccountBalanceApiResponse result = accountBalanceRestClientRest.executeApi(bankAccontParamInputBin);
        return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
    }




}
