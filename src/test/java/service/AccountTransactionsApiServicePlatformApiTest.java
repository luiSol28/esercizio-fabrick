package service;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.entity.RequestAccountTransactionEntity;
import com.esercizio.backend.fabrick.mapper.AccountTransactionMapper;
import com.esercizio.backend.fabrick.model.api.response.AccountTransactionsResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiTransactionsApiResponse;
import com.esercizio.backend.fabrick.repository.AccountTransactionsRepositoryService;
import com.esercizio.backend.fabrick.service.platformApi.api.AccountTransactionsApiServicePlatformApi;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.AccountTransactionRestClientService;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountTransactionsApiServicePlatformApiTest {

    @Mock
    private AccountTransactionsRepositoryService accountTransactionsRepositoryService;

    @Mock
    private AccountTransactionRestClientService accountTransactionRestClientService;

    @Mock
    private AccountTransactionMapper accountTransactionMapper;

    @InjectMocks
    private AccountTransactionsApiServicePlatformApi accountTransactionsApiServicePlatformApi;

    private BankAccontParamInputBin bankAccontParamInputBin;

    @Before
    public void init() {
        bankAccontParamInputBin = BankAccontParamInputBin
                .builder()
                .idAccount("14537780")
                .apiKey("")
                .authSchema("")
                .build();
    }

    @Test
    public void executeApiTest() throws IOException, JSONException {
        Mockito.when(accountTransactionRestClientService.callApiRest(bankAccontParamInputBin)).thenReturn(new PlatformApiTransactionsApiResponse());
        Mockito.when(accountTransactionMapper.fromtoAccountTransactionEntity(bankAccontParamInputBin)).thenReturn(new RequestAccountTransactionEntity());
        Mockito.when(accountTransactionMapper.converterToAccountTransactionsResponse(new PlatformApiTransactionsApiResponse())).thenReturn(new AccountTransactionsResponse());
        Mockito.when(accountTransactionsRepositoryService.retrieveAccountTransactionFromDB(bankAccontParamInputBin)).thenReturn(new ArrayList<>());
        Mockito.when(accountTransactionsRepositoryService.saveAccountTransactionToDB(new PlatformApiTransactionsApiResponse(), bankAccontParamInputBin)).thenReturn(new RequestAccountTransactionEntity());
        ResponseEntity<AccountTransactionsResponse> response = accountTransactionsApiServicePlatformApi.executeApi(bankAccontParamInputBin);
        Assert.assertNotNull(response);
    }
}
