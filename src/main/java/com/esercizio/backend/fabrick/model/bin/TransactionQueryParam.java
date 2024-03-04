package com.esercizio.backend.fabrick.model.bin;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Data
public class TransactionQueryParam {

    private String idAccount;
    private LocalDate fromAccountingDate;
    private LocalDate toAccountingDate;
}
