package com.esercizio.backend.fabrick.model.api;

import lombok.Data;

import java.util.List;

@Data
public class TransactionsResponse {

    private List<Transaction> transactions;
}
