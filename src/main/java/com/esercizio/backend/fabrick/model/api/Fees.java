package com.esercizio.backend.fabrick.model.api;

import lombok.Data;

@Data
public class Fees {

    private String feeCode;
    private String description;
    private String amount;
    private String currency;
}
