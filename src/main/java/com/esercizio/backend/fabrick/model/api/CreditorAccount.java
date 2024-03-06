package com.esercizio.backend.fabrick.model.api;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class CreditorAccount {

    @NotEmpty
    private String accountCode;

    private String bicCode = "";
}
