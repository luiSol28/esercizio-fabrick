package com.esercizio.backend.fabrick.model.platformApi;

import com.esercizio.backend.fabrick.model.api.ExecuteBankTransferResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlatformApiExecuteBankTransferApiResponse extends PlatformApiResponse {

    @JsonProperty("payload")
    private ExecuteBankTransferResponse payload;
}
