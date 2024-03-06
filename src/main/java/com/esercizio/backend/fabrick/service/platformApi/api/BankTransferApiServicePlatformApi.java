package com.esercizio.backend.fabrick.service.platformApi.api;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.model.api.ExecuteBankTransferResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiExecuteBankTransferApiResponse;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.BankTransferRestClientService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BankTransferApiServicePlatformApi implements PlatformApiRestApi<ResponseEntity<ExecuteBankTransferResponse>, BankAccontParamInputBin> {

    @Autowired
    private BankTransferRestClientService bankTransferRestClientService;

    public ResponseEntity<ExecuteBankTransferResponse> executeApi(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {

        PlatformApiExecuteBankTransferApiResponse response = bankTransferRestClientService.callApiRest(bankAccontParamInputBin);
        return new ResponseEntity<>(response.getPayload(), HttpStatus.OK);
    }

}
