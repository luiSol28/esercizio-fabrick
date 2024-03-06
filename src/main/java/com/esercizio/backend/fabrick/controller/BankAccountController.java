package com.esercizio.backend.fabrick.controller;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.model.api.AccountBalance;
import com.esercizio.backend.fabrick.model.api.ExecuteBankTransferResponse;
import com.esercizio.backend.fabrick.model.dto.BankTransferDto;
import com.esercizio.backend.fabrick.service.AccountBalanceService;
import com.esercizio.backend.fabrick.service.BankTransferService;
import com.esercizio.backend.fabrick.service.AccountTransactionsService;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/banckaccount")
public class BankAccountController {

    Logger logger = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private AccountTransactionsService accountTransactionsService;

    @Autowired
    private BankTransferService bankTransferService;


    @GetMapping(value = "/{idAccount}/balance")
    public ResponseEntity<AccountBalance> getBalance(@PathVariable String idAccount,
                                                     @RequestHeader(value = "Content-Type") String contentType,
                                                     @RequestHeader(value = "Auth-Schema") String authSchema,
                                                     @RequestHeader(value = "Api-Key") String apiKey) throws IOException, JSONException {
        logger.info("GET /banckaccount/{}/balance - REQUEST", idAccount);
        ResponseEntity response = accountBalanceService.retrieveBalance(BankAccontParamInputBin.builder()
                .idAccount(idAccount)
                .contentType(contentType)
                .authSchema(authSchema)
                .apiKey(apiKey)
                .build());
        logger.info("GET /banckaccount/{}/balance - RESPONSE = {}", idAccount, response);
        return response;
    }

    @GetMapping(value = "/{idAccount}/transactions")
    public ResponseEntity getTransactions(@PathVariable String idAccount,
                                          @RequestParam("fromAccountingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromAccountingDate,
                                          @RequestParam("toAccountingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toAccountingDate,
                                          @RequestHeader(value = "Content-Type") String contentType,
                                          @RequestHeader(value = "Auth-Schema") String authSchema,
                                          @RequestHeader(value = "Api-Key") String apiKey) throws IOException, JSONException {
        logger.info("GET /banckaccount/{}/transactions - REQUEST", idAccount);
        ResponseEntity response = accountTransactionsService.retrieveAccountTransactions(BankAccontParamInputBin.builder()
                .idAccount(idAccount)
                .contentType(contentType)
                .authSchema(authSchema)
                .apiKey(apiKey)
                .fromAccountingDate(fromAccountingDate.toString())
                .toAccountingDate(toAccountingDate.toString())
                .build());
        logger.info("GET /banckaccount/{}/transactions - RESPONSE = {}", idAccount, response);
        return response;
    }

    @PostMapping(value = "/{idAccount}/banktransfer")
    public ResponseEntity<ExecuteBankTransferResponse> postBankTransfer(@PathVariable String idAccount,
                                                                        @Valid @RequestBody BankTransferDto bankTransferDto,
                                                                        @RequestHeader(value = "Content-Type") String contentType,
                                                                        @RequestHeader(value = "Auth-Schema") String authSchema,
                                                                        @RequestHeader(value = "Api-Key") String apiKey) {
        logger.info("POST /banckaccount/{}/banktransfer - REQUEST with DTO={}", idAccount, bankTransferDto);
        ResponseEntity response = bankTransferService.executeBankTransfer(bankTransferDto);
        logger.info("POST /banckaccount/{}/banktransfer - RESPONSE = {} and DTO={}", idAccount, response, bankTransferDto);
        return response;
    }
}
