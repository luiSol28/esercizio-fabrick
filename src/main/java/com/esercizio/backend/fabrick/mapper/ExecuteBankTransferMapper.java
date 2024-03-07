package com.esercizio.backend.fabrick.mapper;

import com.esercizio.backend.fabrick.model.api.ExecuteBankTransfer;
import com.esercizio.backend.fabrick.model.api.response.ExecuteBankTransferResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiExecuteBankTransferApiResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExecuteBankTransferMapper {

    @Mapping(target = "status", source = "platformApiExecuteBankTransferApiResponse.status")
    @Mapping(target = "statusBankTransfert", source = "executeBankTransfer.status")
    ExecuteBankTransferResponse covertTOExecuteBankTransfer(ExecuteBankTransfer executeBankTransfer, PlatformApiExecuteBankTransferApiResponse platformApiExecuteBankTransferApiResponse);

}
