package repository;

import java.util.List;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.entity.RequestAccountTransactionEntity;
import com.esercizio.backend.fabrick.mapper.AccountTransactionMapper;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiTransactionsApiResponse;
import com.esercizio.backend.fabrick.repository.AccountTransactionsRepository;
import com.esercizio.backend.fabrick.repository.AccountTransactionsRepositoryService;
import com.esercizio.backend.fabrick.specification.RequestAccountTransactionSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;


@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountTransactionsRepositoryServiceTest {

    @Mock
    private AccountTransactionsRepository accountTransactionsRepository;

    @Mock
    private AccountTransactionMapper accountTransactionMapper;

    @InjectMocks
    private AccountTransactionsRepositoryService accountTransactionsRepositoryService;

    private BankAccontParamInputBin bankAccontParamInputBin;

    private RequestAccountTransactionEntity requestAccountTransactionEntity;

    @Before
    public void init() {
        bankAccontParamInputBin = BankAccontParamInputBin
                .builder()
                .idAccount("14537780")
                .apiKey("")
                .authSchema("")
                .build();
        requestAccountTransactionEntity =  new RequestAccountTransactionEntity();
        requestAccountTransactionEntity.setId(1L);
    }

    @Test
    public void retrieveAccountTransactionFromDBTest() {
        Mockito.when(accountTransactionsRepository.findAll(RequestAccountTransactionSpecification.retrieveRequestAccountTransactionByIdAccountAndtoAccountingDateAndFromAccountingDate(bankAccontParamInputBin))).thenReturn(new ArrayList<>());
        List<RequestAccountTransactionEntity> requestAccountTransactionEntityList = accountTransactionsRepositoryService.retrieveAccountTransactionFromDB(bankAccontParamInputBin);
        Assert.assertNotNull(requestAccountTransactionEntityList);
    }

    @Test
    public void saveAccountTransactionToDBTest() throws JsonProcessingException {
        Mockito.when(accountTransactionsRepository.save(requestAccountTransactionEntity)).thenReturn(new RequestAccountTransactionEntity());
        Mockito.when(accountTransactionMapper.fromtoAccountTransactionEntity(bankAccontParamInputBin)).thenReturn(requestAccountTransactionEntity);
        RequestAccountTransactionEntity requestAccountTransactionEntity = accountTransactionsRepositoryService.saveAccountTransactionToDB(new PlatformApiTransactionsApiResponse(), bankAccontParamInputBin);
        Assert.assertNotNull(requestAccountTransactionEntity);
    }

    @Test
    public void saveAccountTransactionToDBTestWithNull() throws JsonProcessingException {
        Mockito.when(accountTransactionsRepository.save(new RequestAccountTransactionEntity())).thenReturn(new RequestAccountTransactionEntity());
        Mockito.when(accountTransactionMapper.fromtoAccountTransactionEntity(bankAccontParamInputBin)).thenReturn(new RequestAccountTransactionEntity());
        RequestAccountTransactionEntity requestAccountTransactionEntity = accountTransactionsRepositoryService.saveAccountTransactionToDB(new PlatformApiTransactionsApiResponse(), bankAccontParamInputBin);
        Assert.assertNull(requestAccountTransactionEntity);
    }

}
