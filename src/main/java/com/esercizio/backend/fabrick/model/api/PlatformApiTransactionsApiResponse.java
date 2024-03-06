package com.esercizio.backend.fabrick.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlatformApiTransactionsApiResponse extends PlatformApiResponse {

    @JsonProperty("payload")
    private AccountTransactionsResponse payload;
}
