package com.esercizio.backend.fabrick.model.api.response;

import com.esercizio.backend.fabrick.model.api.Amount;
import com.esercizio.backend.fabrick.model.api.Creditor;
import com.esercizio.backend.fabrick.model.api.Debtor;
import com.esercizio.backend.fabrick.model.api.Fees;
import com.esercizio.backend.fabrick.model.platformApi.PlatformErrorApiResponse;
import com.esercizio.backend.fabrick.model.platformApi.StatusPlatformApiResponseEnum;
import lombok.Data;

@Data
public class ExecuteBankTransferResponse  {

    private StatusPlatformApiResponseEnum status;

    private PlatformErrorApiResponse[] errors;

    private String moneyTransferId;

    private String statusBankTransfert;

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
