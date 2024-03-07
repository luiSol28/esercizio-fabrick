package com.esercizio.backend.fabrick.model.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AccountBalance implements Serializable {

  @JsonDeserialize(using = LocalDateDeserializer.class)
   private LocalDate date;

    private BigDecimal balance;

    private BigDecimal availableBalance;

    private String currency;
}
