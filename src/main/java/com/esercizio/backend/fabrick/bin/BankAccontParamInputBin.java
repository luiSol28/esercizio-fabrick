package com.esercizio.backend.fabrick.bin;

import com.esercizio.backend.fabrick.model.dto.api.BankTransferDto;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BankAccontParamInputBin extends PlatformApiParamInput {
    private String idAccount;
    private String toAccountingDate;
    private String fromAccountingDate;
    private BankTransferDto bankTransferDto;

}
