package com.esercizio.backend.fabrick.model.api;

import lombok.Data;

@Data
public class Amount {

    private String debtorAmount;
    private String debtorCurrency;
    private String creditorAmount;
    private String creditorCurrency;
    private String creditorCurrencyDate;
    private String exchangeRate;
    private String currencyRatio;
}
