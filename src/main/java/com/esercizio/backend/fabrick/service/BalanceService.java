package com.esercizio.backend.fabrick.service;

import com.esercizio.backend.fabrick.model.api.AccountBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    @Autowired
    private HttpClientService httpClientService;

    public ResponseEntity<AccountBalance> retrieveBalance(String idAccount) {
        return new ResponseEntity<>(new AccountBalance(), HttpStatus.OK);
    }
}
