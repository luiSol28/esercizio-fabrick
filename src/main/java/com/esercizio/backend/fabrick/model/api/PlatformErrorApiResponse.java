package com.esercizio.backend.fabrick.model.api;

import lombok.Data;

@Data
public class PlatformErrorApiResponse {

    private String code;
    private String description;
    private String params;
}
