package com.esercizio.backend.fabrick.service.platformApi.clientRest;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiAccountBalanceApiResponse;
import com.esercizio.backend.fabrick.service.common.HttpClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountBalanceRestClientService implements ClientRestApi<PlatformApiAccountBalanceApiResponse, BankAccontParamInputBin> {

    @Autowired
    private HttpClientService httpClientService;

    @Value("${platformapifabrick.accountBalance.url}")
    private String urlTemplate;

    @Value("${platformapifabrick.accountBalance.mock}")
    private Boolean mock;

    @Autowired
    private ResourceLoader resourceLoader;

    public PlatformApiAccountBalanceApiResponse callApiRest(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {
        if (Boolean.TRUE.equals(mock)) {
            HttpEntity response = httpClientService.executeGet(prepareHttpClientRequestBin(bankAccontParamInputBin, urlTemplate));
            return preparePlatformApiResponse(response);
        } else {
            return covertObjectInJSONMock();
        }
    }

    private PlatformApiAccountBalanceApiResponse preparePlatformApiResponse(HttpEntity response) throws IOException {
        String resultString = UtilityClassRestClient.convertStreamToString(response.getContent());
        return covertObjectInJSON(resultString);
    }

    private PlatformApiAccountBalanceApiResponse covertObjectInJSON(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, PlatformApiAccountBalanceApiResponse.class);
    }

    private PlatformApiAccountBalanceApiResponse covertObjectInJSONMock() throws IOException {
        final Resource fileResource = resourceLoader.getResource("classpath:mock/PlatformApiAccountBalanceMock.json");
        String jsonString = UtilityClassRestClient.convertStreamToString(fileResource.getInputStream());
        return covertObjectInJSON(jsonString);
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
