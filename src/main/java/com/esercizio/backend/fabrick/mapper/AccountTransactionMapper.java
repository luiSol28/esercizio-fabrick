package com.esercizio.backend.fabrick.mapper;


import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.entity.RequestAccountTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountTransactionMapper {

    @Mapping(target = "idaccount", source = "bankAccontParamInputBin.idAccount")
    @Mapping(target = "fromaccountingdate", source = "bankAccontParamInputBin.fromAccountingDate")
    @Mapping(target = "toaccountingdate", source = "bankAccontParamInputBin.toAccountingDate")
    RequestAccountTransactionEntity fromtoAccountTransactionEntity (BankAccontParamInputBin bankAccontParamInputBin);
}
