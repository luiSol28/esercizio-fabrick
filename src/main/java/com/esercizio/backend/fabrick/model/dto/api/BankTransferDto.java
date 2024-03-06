package com.esercizio.backend.fabrick.model.dto.api;

import com.esercizio.backend.fabrick.model.api.Creditor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BankTransferDto {

    @NotNull
    @Valid
    private Creditor creditor;

    @NotEmpty
    private String description;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.NUMBER)
    private BigDecimal amount;

    @NotEmpty
    private String currency;

    private String executionDate;
}
