package com.esercizio.backend.fabrick.entity;

import com.esercizio.backend.fabrick.model.api.TypeTransaction;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "t_account_transaction")
@Data
public class AccountTransactionEntity {

    @Id
    @Column(name = "transactionId")
    private BigInteger transactionId;

    @Column(name = "idAccount")
    private BigInteger idAccount;

    @Column(name = "operationId")
    private BigInteger operationId;

    @Column(name = "accountingDate")
    private LocalDate accountingDate;

    @Column(name = "valueDate")
    private LocalDate valueDate;

    @Column(name = "type_enumeration")
    private String enumeration;

    @Column(name = "type_value")
    private String value;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "description")
    private String description;
}
