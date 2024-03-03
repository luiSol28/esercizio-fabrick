package com.esercizio.backend.fabrick.controller;

import com.esercizio.backend.fabrick.model.dto.BankTransferDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @GetMapping(value = "/{idAccount}/balance")
    public ResponseEntity getBalance(@PathVariable Long idAccount) {
        logger.info("GET /banckaccount/{}/balance", idAccount);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/{idAccount}/transactions")
    public ResponseEntity getTransactions(@PathVariable Long idAccount,
                                          @RequestParam ("fromAccountingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromAccountingDate,
                                          @RequestParam ("toAccountingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toAccountingDate) {
        logger.info("GET /banckaccount/{}/transactions", idAccount);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/{idAccount}/banktransfer")
    public ResponseEntity postBankTransfer(@PathVariable Long idAccount, @Valid @RequestBody BankTransferDto bankTransferDto) {
        logger.info("POST /banckaccount/{}/banktransfer and dto={}", idAccount, bankTransferDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
