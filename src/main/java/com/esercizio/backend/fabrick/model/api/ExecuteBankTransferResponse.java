package com.esercizio.backend.fabrick.model.api;

import com.esercizio.backend.fabrick.enums.StatusBankTransfertEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ExecuteBankTransferResponse {

    private String moneyTransferId;
    private StatusBankTransfertEnum status;
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
