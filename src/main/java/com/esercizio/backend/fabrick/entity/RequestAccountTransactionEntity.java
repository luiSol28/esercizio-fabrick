package com.esercizio.backend.fabrick.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "t_richieste_account_transaction")
@Data
@Sql({"/schema.sql"})
public class RequestAccountTransactionEntity {

    public static final String FIELD_ID_ACCOUNT = "idaccount";
    public static final String FIELD_FROM_ACCOUNTING_DATE = "fromaccountingdate";
    public static final String FIELD_TO_ACCOUNTING_DATE = "toaccountingdate";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "idaccount")
    private BigInteger idaccount;

   // @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "fromaccountingdate")
    private String fromaccountingdate;

   // @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "toaccountingdate")
    private String toaccountingdate;

    @Column(name = "content")
    private String content;

}
