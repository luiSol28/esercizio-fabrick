package com.esercizio.backend.fabrick.model.api.response;

import com.esercizio.backend.fabrick.model.api.AccountBalance;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class CashAccountBalanceResponse extends AccountBalanceResponse {

    private AccountBalance payload;
}
