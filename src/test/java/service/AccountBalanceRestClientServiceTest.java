package service;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import com.esercizio.backend.fabrick.model.api.response.CashAccountBalanceResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiAccountBalanceApiResponse;
import com.esercizio.backend.fabrick.service.common.HttpClientService;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.AccountBalanceRestClientService;
import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountBalanceRestClientServiceTest  {

    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private AccountBalanceRestClientService accountBalanceRestClientService;

    private BankAccontParamInputBin bankAccontParamInputBin;

    private HttpClientRequestBin httpClientRequestBin;

    @Before
    public void init() {
        bankAccontParamInputBin = BankAccontParamInputBin
                .builder()
                .idAccount("14537780")
                .apiKey("")
                .authSchema("")
                .build();

        httpClientRequestBin = prepareHttpClientRequestBin(bankAccontParamInputBin, "");

    }

    @Test
    public void executeApiTest() throws IOException, JSONException {
        HttpEntity mock = mock(HttpEntity.class);
        Mockito.when(httpClientService.executeGet(httpClientRequestBin)).thenReturn((mock));
        PlatformApiAccountBalanceApiResponse response = accountBalanceRestClientService.callApiRest(bankAccontParamInputBin);
        Assert.assertNotNull(response);
    }

    public HttpClientRequestBin prepareHttpClientRequestBin(BankAccontParamInputBin bankAccontParamInputBin, String urlTemplate) {
        return HttpClientRequestBin.builder()
                .urlTemplate(urlTemplate)
                .uriParam(prepareUriParamMap(bankAccontParamInputBin))
                .header(com.esercizio.backend.fabrick.service.platformApi.clientRest.UtilityClassRestClient.prepareHeaderMap(bankAccontParamInputBin))
                .build();
    }

    public Map<String, String> prepareUriParamMap(BankAccontParamInputBin bankAccontParamInputBin) {
        Map<String, String> uriParam = new HashMap<>();
        uriParam.put("accountId", bankAccontParamInputBin.getIdAccount());
        return uriParam;
    }
}
