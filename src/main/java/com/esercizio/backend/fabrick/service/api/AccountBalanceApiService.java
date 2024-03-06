package com.esercizio.backend.fabrick.service.api;

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
public class AccountBalanceApiService implements RestApi<ResponseEntity<AccountBalance>,BankAccontParamInputBin > {

    Logger logger = LoggerFactory.getLogger(AccountBalanceApiService.class);

    @Autowired
    private AccountBalanceRestClientService accountBalanceRestClientService;

    public ResponseEntity<AccountBalance> executeApi(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {
        return retrieveBalance(bankAccontParamInputBin);
    }

    private ResponseEntity<AccountBalance> retrieveBalance(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {
        PlatformApiAccountBalanceApiResponse result = accountBalanceRestClientService.callApiRest(bankAccontParamInputBin);
        return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
    }

}
