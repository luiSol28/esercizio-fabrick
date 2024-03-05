package com.esercizio.backend.fabrick.service;

import com.esercizio.backend.fabrick.model.api.TransactionsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {

    public ResponseEntity<TransactionsResponse> retrieveTransactions(Object transactionQueryParamBin) {
        return new ResponseEntity<>(new TransactionsResponse(), HttpStatus.OK);
    }
}
