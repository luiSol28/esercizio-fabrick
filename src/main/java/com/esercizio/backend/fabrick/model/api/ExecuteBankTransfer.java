package com.esercizio.backend.fabrick.model.api;

import lombok.Data;

@Data
public class ExecuteBankTransfer {
    private String moneyTransferId;

    private String status;

    private String direction;

    private Creditor creditor;

    private Debtor debtor;

    private String cro;

    private String trn;

    private String uri;

    private String description;

    private String createdDatetime;

    private String accountedDatetime;

    private String debtorValueDate;

    private String creditorValueDate;

    private Boolean isUrgent;

    private Boolean isInstant;

    private String feeType;

    private String feeAccountId;

    private Fees[] fees;

    private Boolean hasTaxRelief;

    private Amount amount;
}
