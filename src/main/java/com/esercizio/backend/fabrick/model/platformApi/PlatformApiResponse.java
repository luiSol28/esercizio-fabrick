package com.esercizio.backend.fabrick.model.platformApi;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class PlatformApiResponse {

    @JsonProperty("status")
    private StatusPlatformApiResponseEnum status;

    @JsonProperty("errors")
    @JsonAlias("error")
    private PlatformErrorApiResponse[] errors;

}
