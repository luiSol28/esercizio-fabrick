package com.esercizio.backend.fabrick.model.api.response;

import com.esercizio.backend.fabrick.model.platformApi.PlatformErrorApiResponse;
import com.esercizio.backend.fabrick.model.platformApi.StatusPlatformApiResponseEnum;
import lombok.Data;

@Data
public class AccountBalanceResponse {

    private StatusPlatformApiResponseEnum status;
    private PlatformErrorApiResponse[] errors;
}
