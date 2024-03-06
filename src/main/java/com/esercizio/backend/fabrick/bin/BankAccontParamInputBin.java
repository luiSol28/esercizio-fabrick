package com.esercizio.backend.fabrick.bin;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BankAccontParamInputBin {
    private String idAccount;
    private String contentType;
    private String authSchema;
    private String apiKey;
    private LocalDate fromAccountingDate;
    private LocalDate toAccountingDate;
}
