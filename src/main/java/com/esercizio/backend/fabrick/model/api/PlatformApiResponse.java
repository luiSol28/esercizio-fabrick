package com.esercizio.backend.fabrick.model.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlatformApiResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("errors")
    @JsonAlias("error")
    private List<PlatformErrorApiResponse> errors;
}
