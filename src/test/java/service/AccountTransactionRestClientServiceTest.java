package service;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiAccountBalanceApiResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiTransactionsApiResponse;
import com.esercizio.backend.fabrick.service.common.HttpClientService;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.AccountBalanceRestClientService;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.AccountTransactionRestClientService;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountTransactionRestClientServiceTest {

    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private AccountTransactionRestClientService accountTransactionRestClientService;

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
        PlatformApiTransactionsApiResponse response = accountTransactionRestClientService.callApiRest(bankAccontParamInputBin);
        Assert.assertNotNull(response);
    }


    public Map<String, String> prepareUriParamMap(BankAccontParamInputBin bankAccontParamInputBin) {
        Map<String, String> uriParam = new HashMap<>();
        uriParam.put("accountId", bankAccontParamInputBin.getIdAccount());
        return uriParam;
    }

    public HttpClientRequestBin prepareHttpClientRequestBin(BankAccontParamInputBin bankAccontParamInputBin, String urlTemplate) {
        return HttpClientRequestBin.builder()
                .urlTemplate(urlTemplate)
                .queryParam(prepareQueryParamMap(bankAccontParamInputBin))
                .uriParam(prepareUriParamMap(bankAccontParamInputBin))
                .header(com.esercizio.backend.fabrick.service.platformApi.clientRest.UtilityClassRestClient.prepareHeaderMap(bankAccontParamInputBin))
                .build();
    }

    public MultiValueMap<String, String> prepareQueryParamMap(BankAccontParamInputBin bankAccontParamInputBin) {
        MultiValueMap<String, String> queryParam = new LinkedMultiValueMap<>();
        if (bankAccontParamInputBin.getFromAccountingDate() != null) {
            queryParam.put("fromAccountingDate", Arrays.asList(bankAccontParamInputBin.getFromAccountingDate().toString()));

        }
        if (bankAccontParamInputBin.getToAccountingDate() != null) {
            queryParam.put("toAccountingDate", Arrays.asList(bankAccontParamInputBin.getToAccountingDate().toString()));

        }
        return queryParam;
    }
}
