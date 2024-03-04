package com.esercizio.backend.fabrick.model.api;

import com.esercizio.backend.fabrick.enums.StatusBankTransfertEnum;
import lombok.Data;

@Data
public class ExecuteBankTransferResponse {

    private StatusBankTransfertEnum status;
}
