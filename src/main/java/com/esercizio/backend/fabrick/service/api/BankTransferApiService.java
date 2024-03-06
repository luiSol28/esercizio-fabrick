package com.esercizio.backend.fabrick.service.api;

import com.esercizio.backend.fabrick.model.api.ExecuteBankTransferResponse;
import com.esercizio.backend.fabrick.model.dto.BankTransferDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BankTransferApiService {

    public ResponseEntity<ExecuteBankTransferResponse> executeBankTransfer(BankTransferDto bankTransferDto) {
        return new ResponseEntity<>(new ExecuteBankTransferResponse(), HttpStatus.OK);
    }
}
