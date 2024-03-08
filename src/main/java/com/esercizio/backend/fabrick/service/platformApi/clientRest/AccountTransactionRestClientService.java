package com.esercizio.backend.fabrick.service.platformApi.clientRest;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiAccountBalanceApiResponse;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiTransactionsApiResponse;
import com.esercizio.backend.fabrick.service.common.HttpClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountTransactionRestClientService implements com.esercizio.backend.fabrick.service.platformApi.clientRest.ClientRestApi<PlatformApiTransactionsApiResponse, BankAccontParamInputBin> {

    Logger logger = LoggerFactory.getLogger(AccountTransactionRestClientService.class);

    @Autowired
    private HttpClientService httpClientService;

    @Value("${platformapifabrick.accountransactions.url}")
    private String urlTemplate = "";

    @Value("${platformapifabrick.accountransactions.mock}")
    private Boolean mock = Boolean.FALSE;

    @Autowired
    private ResourceLoader resourceLoader;

    public PlatformApiTransactionsApiResponse callApiRest(BankAccontParamInputBin bankAccontParamInputBin) throws IOException {
        if (Boolean.FALSE.equals(mock)) {
            HttpEntity response = httpClientService.executeGet(prepareHttpClientRequestBin(bankAccontParamInputBin, urlTemplate));
            return preparePlatformApiResponse(response);
        } else return covertObjectInJSONMock();
    }


    private PlatformApiTransactionsApiResponse preparePlatformApiResponse(HttpEntity response) throws IOException {
        String resultString = UtilityClassRestClient.convertStreamToString(response.getContent());
        return covertObjectInJSON(resultString);
    }

    private PlatformApiTransactionsApiResponse covertObjectInJSON(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (StringUtils.isEmpty(jsonString))
            return new PlatformApiTransactionsApiResponse();
        else
            return objectMapper.readValue(jsonString, PlatformApiTransactionsApiResponse.class);
    }

    private PlatformApiTransactionsApiResponse covertObjectInJSONMock() throws IOException {
        final Resource fileResource = resourceLoader.getResource("classpath:mock/PlatformApiTransactionsApiResponseMock.json");
        String jsonString = UtilityClassRestClient.convertStreamToString(fileResource.getInputStream());
        return covertObjectInJSON(jsonString);
    }

    public HttpClientRequestBin prepareHttpClientRequestBin(BankAccontParamInputBin bankAccontParamInputBin, String urlTemplate) {
        return HttpClientRequestBin.builder()
                .urlTemplate(urlTemplate)
                .queryParam(prepareQueryParamMap(bankAccontParamInputBin))
                .uriParam(prepareUriParamMap(bankAccontParamInputBin))
                .header(com.esercizio.backend.fabrick.service.platformApi.clientRest.UtilityClassRestClient.prepareHeaderMap(bankAccontParamInputBin))
                .build();
    }

    public Map<String, String> prepareUriParamMap(BankAccontParamInputBin bankAccontParamInputBin) {
        Map<String, String> uriParam = new HashMap<>();
        uriParam.put("accountId", bankAccontParamInputBin.getIdAccount());
        return uriParam;
    }

    public MultiValueMap<String, String> prepareQueryParamMap(BankAccontParamInputBin bankAccontParamInputBin) {
        MultiValueMap<String, String> queryParam = new LinkedMultiValueMap<>();
        if (bankAccontParamInputBin.getFromAccountingDate() != null) {
            queryParam.put("fromAccountingDate", Arrays.asList(bankAccontParamInputBin.getFromAccountingDate()));

        }
        if (bankAccontParamInputBin.getToAccountingDate() != null) {
            queryParam.put("toAccountingDate", Arrays.asList(bankAccontParamInputBin.getToAccountingDate()));

        }
        return queryParam;
    }

}
