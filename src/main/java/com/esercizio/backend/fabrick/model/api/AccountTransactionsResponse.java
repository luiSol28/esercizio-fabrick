package com.esercizio.backend.fabrick.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AccountTransactionsResponse {

    @JsonProperty("list")
    private List<AccountTransaction> list;
}
