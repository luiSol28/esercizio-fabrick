package service;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.mapper.AccountBalanceResponseMapper;
import com.esercizio.backend.fabrick.model.api.response.CashAccountBalanceResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiAccountBalanceApiResponse;
import com.esercizio.backend.fabrick.service.platformApi.api.AccountBalanceApiServicePlatformApi;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.AccountBalanceRestClientService;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountBalanceApiServicePlatformApiTest {

    @Mock
    private AccountBalanceRestClientService accountBalanceRestClientService;

    @Mock
    private AccountBalanceResponseMapper accountBalanceResponseMapper;

    @InjectMocks
    private AccountBalanceApiServicePlatformApi accountBalanceApiServicePlatformApi;

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
        Mockito.when(accountBalanceRestClientService.callApiRest(bankAccontParamInputBin)).thenReturn(new PlatformApiAccountBalanceApiResponse());
        Mockito.when(accountBalanceResponseMapper.convertToAccountBalanceResponsefromPlatformApiAccountBalanceApiResponse(new PlatformApiAccountBalanceApiResponse())).thenReturn(new CashAccountBalanceResponse());
        ResponseEntity<CashAccountBalanceResponse> response = accountBalanceApiServicePlatformApi.executeApi(bankAccontParamInputBin);
        Assert.assertNotNull(response);
    }

}
