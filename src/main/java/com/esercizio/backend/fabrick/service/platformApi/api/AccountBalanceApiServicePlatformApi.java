package com.esercizio.backend.fabrick.service.platformApi.api;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.mapper.AccountBalanceResponseMapper;
import com.esercizio.backend.fabrick.model.api.response.AccountBalanceResponse;
import com.esercizio.backend.fabrick.model.api.response.CashAccountBalanceResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiAccountBalanceApiResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformErrorApiResponse;
import com.esercizio.backend.fabrick.model.platformApi.StatusPlatformApiResponseEnum;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.AccountBalanceRestClientService;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccountBalanceApiServicePlatformApi implements PlatformApiRestApi<ResponseEntity<CashAccountBalanceResponse>, BankAccontParamInputBin> {

    Logger logger = LoggerFactory.getLogger(AccountBalanceApiServicePlatformApi.class);

    @Autowired
    private AccountBalanceRestClientService accountBalanceRestClientService;

    @Autowired
    private AccountBalanceResponseMapper accountBalanceResponseMapper;

    public ResponseEntity<CashAccountBalanceResponse> executeApi(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {
        return retrieveBalance(bankAccontParamInputBin);
    }

    private ResponseEntity<CashAccountBalanceResponse> retrieveBalance(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {
        PlatformApiAccountBalanceApiResponse result = accountBalanceRestClientService.callApiRest(bankAccontParamInputBin);
        return prepareResposeEntity(result);
    }

    private ResponseEntity<CashAccountBalanceResponse> prepareResposeEntity(PlatformApiAccountBalanceApiResponse result) {
        if (StatusPlatformApiResponseEnum.KO.equals(result.getStatus())) {
            CashAccountBalanceResponse cashAccountBalanceResponse = new CashAccountBalanceResponse();
            cashAccountBalanceResponse.setStatus(result.getStatus());
            cashAccountBalanceResponse.setErrors(result.getErrors());
            return new ResponseEntity<>(cashAccountBalanceResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(accountBalanceResponseMapper.convertToAccountBalanceResponsefromPlatformApiAccountBalanceApiResponse(result), HttpStatus.OK);
    }

}
