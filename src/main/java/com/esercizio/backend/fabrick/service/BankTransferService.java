package com.esercizio.backend.fabrick.service;

import com.esercizio.backend.fabrick.model.api.ExecuteBankTransferResponse;
import com.esercizio.backend.fabrick.model.dto.BankTransferDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BankTransferService {

    public ResponseEntity<ExecuteBankTransferResponse> executeBankTransfer(BankTransferDto bankTransferDto) {
        return new ResponseEntity<>(new ExecuteBankTransferResponse(), HttpStatus.OK);
    }
}
