package com.esercizio.backend.fabrick.specification;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.entity.RequestAccountTransactionEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class RequestAccountTransactionSpecification {

    public static Specification<RequestAccountTransactionEntity> retrieveRequestAccountTransactionByIdAccountAndtoAccountingDateAndFromAccountingDate(BankAccontParamInputBin bankAccontParamInputBin) {
        return RequestAccountTransactionSpecification.withIdAccounto(bankAccontParamInputBin)
                .and(RequestAccountTransactionSpecification.withFromAccountingDateo(bankAccontParamInputBin))
                .and(RequestAccountTransactionSpecification.withToAccountingDate(bankAccontParamInputBin));
    }

    public static Specification<RequestAccountTransactionEntity> withIdAccounto(BankAccontParamInputBin bankAccontParamInputBin) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(RequestAccountTransactionEntity.FIELD_ID_ACCOUNT), bankAccontParamInputBin.getIdAccount());
    }

    public static Specification<RequestAccountTransactionEntity> withToAccountingDate(BankAccontParamInputBin bankAccontParamInputBin) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(RequestAccountTransactionEntity.FIELD_TO_ACCOUNTING_DATE), bankAccontParamInputBin.getToAccountingDate());
    }

    public static Specification<RequestAccountTransactionEntity> withFromAccountingDateo(BankAccontParamInputBin bankAccontParamInputBin) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(RequestAccountTransactionEntity.FIELD_FROM_ACCOUNTING_DATE), bankAccontParamInputBin.getFromAccountingDate());
    }
}
