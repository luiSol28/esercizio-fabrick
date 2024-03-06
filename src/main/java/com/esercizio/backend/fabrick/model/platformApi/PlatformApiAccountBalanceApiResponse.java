package com.esercizio.backend.fabrick.model.platformApi;

import com.esercizio.backend.fabrick.model.api.AccountBalance;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlatformApiAccountBalanceApiResponse extends PlatformApiResponse {

    @JsonProperty("payload")
    private AccountBalance payload;
}
