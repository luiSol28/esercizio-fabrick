package com.esercizio.backend.fabrick.model.api;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Creditor {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Valid
    private CreditorAccount account;

}
