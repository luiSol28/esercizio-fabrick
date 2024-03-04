package com.esercizio.backend.fabrick.model.api;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Transaction {

    private String transactionId;

    private String operationId;

    private LocalDate accountingDate;

    private LocalDate valueDate;

    private String type;

    private BigDecimal amount;

    private String currency;

    private String description;
}
