package com.esercizio.backend.fabrick.mapper;

import com.esercizio.backend.fabrick.model.api.response.CashAccountBalanceResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiAccountBalanceApiResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountBalanceResponseMapper {

    CashAccountBalanceResponse convertToAccountBalanceResponsefromPlatformApiAccountBalanceApiResponse(PlatformApiAccountBalanceApiResponse platformApiAccountBalanceApiResponse);
}
