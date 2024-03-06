package com.esercizio.backend.fabrick.model.platformApi;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlatformApiResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("errors")
    @JsonAlias("error")
    private PlatformErrorApiResponse[] errors;
}
