package com.esercizio.backend.fabrick.model.api;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Debtor {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Valid
    private CreditorAccount account;
}
