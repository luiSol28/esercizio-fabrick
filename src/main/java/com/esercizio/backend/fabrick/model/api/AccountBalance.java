package com.esercizio.backend.fabrick.model.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AccountBalance {

    private LocalDate date;

    private BigDecimal balance;

    private BigDecimal availableBalance;

    private String currency;
}
