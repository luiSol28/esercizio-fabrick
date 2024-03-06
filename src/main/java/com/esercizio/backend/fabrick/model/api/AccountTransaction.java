package com.esercizio.backend.fabrick.model.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AccountTransaction {

    private String transactionId;

    private String operationId;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate accountingDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate valueDate;

    private TypeTransaction type;

    private BigDecimal amount;

    private String currency;

    private String description;
}
