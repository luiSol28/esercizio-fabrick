package com.esercizio.backend.fabrick.bin;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PlatformApiParamInput {
    private String contentType;
    private String authSchema;
    private String apiKey;
}
