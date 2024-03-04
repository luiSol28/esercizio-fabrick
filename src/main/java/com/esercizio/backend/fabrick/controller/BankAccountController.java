package com.esercizio.backend.fabrick.controller;

import com.esercizio.backend.fabrick.model.api.AccountBalance;
import com.esercizio.backend.fabrick.model.api.ExecuteBankTransferResponse;
import com.esercizio.backend.fabrick.model.bin.TransactionQueryParam;
import com.esercizio.backend.fabrick.model.dto.BankTransferDto;
import com.esercizio.backend.fabrick.service.BalanceService;
import com.esercizio.backend.fabrick.service.BankTransferService;
import com.esercizio.backend.fabrick.service.TransactionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/banckaccount")
public class BankAccountController {

    Logger logger = LoggerFactory.getLogger(BankAccountController.class);

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private BankTransferService bankTransferService;


    @GetMapping(value = "/{idAccount}/balance")
    public ResponseEntity<AccountBalance> getBalance(@PathVariable String idAccount) {
        logger.info("GET /banckaccount/{}/balance - REQUEST", idAccount);
        ResponseEntity response = balanceService.retrieveBalance(idAccount);
        logger.info("GET /banckaccount/{}/balance - RESPONSE = {}", idAccount, response);
        return response;
    }

    @GetMapping(value = "/{idAccount}/transactions")
    public ResponseEntity getTransactions(@PathVariable String idAccount,
                                          @RequestParam("fromAccountingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromAccountingDate,
                                          @RequestParam("toAccountingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toAccountingDate) {
        logger.info("GET /banckaccount/{}/transactions - REQUEST", idAccount);
        ResponseEntity response = transactionsService.retrieveTransactions(new TransactionQueryParam());
        logger.info("GET /banckaccount/{}/transactions - RESPONSE = {}", idAccount, response);
        return response;
    }

    @PostMapping(value = "/{idAccount}/banktransfer")
    public ResponseEntity<ExecuteBankTransferResponse> postBankTransfer(@PathVariable String idAccount, @Valid @RequestBody BankTransferDto bankTransferDto) {
        logger.info("POST /banckaccount/{}/banktransfer - REQUEST with DTO={}", idAccount, bankTransferDto);
        ResponseEntity response = bankTransferService.executeBankTransfer(bankTransferDto);
        logger.info("POST /banckaccount/{}/banktransfer - RESPONSE = {} and DTO={}", idAccount, response, bankTransferDto);
        return response;
    }
}
