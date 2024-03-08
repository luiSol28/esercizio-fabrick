package service;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import com.esercizio.backend.fabrick.model.dto.api.BankTransferDto;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiExecuteBankTransferApiResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiTransactionsApiResponse;
import com.esercizio.backend.fabrick.service.common.HttpClientService;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.AccountTransactionRestClientService;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.BankTransferRestClientService;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.UtilityClassRestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BankTransferRestClientServiceTest {

    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private BankTransferRestClientService bankTransferRestClientService;

    private BankAccontParamInputBin bankAccontParamInputBin;

    private HttpClientRequestBin httpClientRequestBin;

    @Before
    public void init() throws JsonProcessingException {
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
        Mockito.when(httpClientService.executePost(httpClientRequestBin)).thenReturn((mock));
        PlatformApiExecuteBankTransferApiResponse response = bankTransferRestClientService.callApiRest(bankAccontParamInputBin);
        Assert.assertNotNull(response);
    }

    public HttpClientRequestBin prepareHttpClientRequestBin(BankAccontParamInputBin bankAccontParamInputBin, String urlTemplate) throws JsonProcessingException {
        return HttpClientRequestBin.builder()
                .urlTemplate(urlTemplate)
                .uriParam(prepareUriParamMap(bankAccontParamInputBin))
                .header(UtilityClassRestClient.prepareHeaderMap(bankAccontParamInputBin))
                .payload(covertPayload(bankAccontParamInputBin.getBankTransferDto()))
                .build();
    }


    public Map<String, String> prepareUriParamMap(BankAccontParamInputBin bankAccontParamInputBin) {
        Map<String, String> uriParam = new HashMap<>();
        uriParam.put("accountId", bankAccontParamInputBin.getIdAccount());
        return uriParam;
    }

    private String covertPayload(BankTransferDto bankTransferDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(bankTransferDto);
    }

}
