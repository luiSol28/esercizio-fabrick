package com.esercizio.backend.fabrick.model.api.response;

import com.esercizio.backend.fabrick.model.api.AccountTransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AccountTransactionsResponse extends AccountBalanceResponse {

    @JsonProperty("list")
    private List<AccountTransaction> list;
}
