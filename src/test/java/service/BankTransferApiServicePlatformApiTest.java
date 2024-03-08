package service;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.mapper.ExecuteBankTransferMapper;
import com.esercizio.backend.fabrick.model.api.ExecuteBankTransfer;
import com.esercizio.backend.fabrick.model.api.response.CashAccountBalanceResponse;
import com.esercizio.backend.fabrick.model.api.response.ExecuteBankTransferResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiAccountBalanceApiResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiExecuteBankTransferApiResponse;
import com.esercizio.backend.fabrick.service.platformApi.api.BankTransferApiServicePlatformApi;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.BankTransferRestClientService;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.UtilityClassRestClient;
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

@RunWith(MockitoJUnitRunner.Silent.class)
public class BankTransferApiServicePlatformApiTest {

    @Mock
    private BankTransferRestClientService bankTransferRestClientService;

    @Mock
    private ExecuteBankTransferMapper executeBankTransferMapper;


    @InjectMocks
    private BankTransferApiServicePlatformApi bankTransferApiServicePlatformApi;

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
        Mockito.when(bankTransferRestClientService.callApiRest(bankAccontParamInputBin)).thenReturn(new PlatformApiExecuteBankTransferApiResponse());
        Mockito.when(executeBankTransferMapper.covertTOExecuteBankTransfer(new ExecuteBankTransfer(), new PlatformApiExecuteBankTransferApiResponse())).thenReturn(new ExecuteBankTransferResponse());
        ResponseEntity<ExecuteBankTransferResponse> response = bankTransferApiServicePlatformApi.executeApi(bankAccontParamInputBin);
        Assert.assertNotNull(response);
    }
}
