package com.esercizio.backend.fabrick.model.platformApi;

import com.esercizio.backend.fabrick.model.api.ExecuteBankTransfer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlatformApiExecuteBankTransferApiResponse extends PlatformApiResponse {

    @JsonProperty("status")
    private StatusPlatformApiResponseEnum status;

    @JsonProperty("errors")
    @JsonAlias("error")
    private PlatformErrorApiResponse[] errors;

    @JsonProperty("payload")
    private ExecuteBankTransfer payload;

}
