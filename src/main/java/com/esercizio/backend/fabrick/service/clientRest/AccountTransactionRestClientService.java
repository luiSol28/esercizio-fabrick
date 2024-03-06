package com.esercizio.backend.fabrick.service.clientRest;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import com.esercizio.backend.fabrick.model.api.PlatformApiTransactionsApiResponse;
import com.esercizio.backend.fabrick.service.common.HttpClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountTransactionRestClientService implements ClienteRestApi<PlatformApiTransactionsApiResponse, BankAccontParamInputBin> {

    Logger logger = LoggerFactory.getLogger(AccountTransactionRestClientService.class);

    @Autowired
    private HttpClientService httpClientService;

    @Value("${platformapifabrick.accountransactions.url}")
    private String urlTemplate;

    public PlatformApiTransactionsApiResponse executeApi(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {
        HttpEntity response = httpClientService.executeGet(prepareHttpClientRequestBin(bankAccontParamInputBin, urlTemplate));
        return preparePlatformApiResponse(response);
    }


    private PlatformApiTransactionsApiResponse preparePlatformApiResponse(HttpEntity response) throws IOException {
        String resultString = UtilityClassRestClient.convertStreamToString(response.getContent());
        return covertObjectInJSON(resultString);
    }

    private PlatformApiTransactionsApiResponse covertObjectInJSON(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, PlatformApiTransactionsApiResponse.class);
    }

    public HttpClientRequestBin prepareHttpClientRequestBin(BankAccontParamInputBin bankAccontParamInputBin, String urlTemplate) {
        return HttpClientRequestBin.builder()
                .urlTemplate(urlTemplate)
                .queryParam(prepareQueryParamMap(bankAccontParamInputBin))
                .uriParam(prepareUriParamMap(bankAccontParamInputBin))
                .header(UtilityClassRestClient.prepareHeaderMap(bankAccontParamInputBin))
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
            queryParam.put("fromAccountingDate", Arrays.asList(bankAccontParamInputBin.getFromAccountingDate().toString()));

        }
        if (bankAccontParamInputBin.getToAccountingDate() != null) {
            queryParam.put("toAccountingDate", Arrays.asList(bankAccontParamInputBin.getToAccountingDate().toString()));

        }
        return queryParam;
    }


}
