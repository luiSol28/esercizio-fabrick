package com.esercizio.backend.fabrick.service.clientRest;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.model.api.PlatformApiAccountBalanceApiResponse;
import com.esercizio.backend.fabrick.service.common.ClienteRestApi;
import com.esercizio.backend.fabrick.service.common.HttpClientService;
import com.esercizio.backend.fabrick.service.common.UtilityClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccountBalanceRestClientRest implements ClienteRestApi<PlatformApiAccountBalanceApiResponse, BankAccontParamInputBin> {

    @Autowired
    private HttpClientService httpClientService;

    @Value("${platformapifabrick.accountBalance.url}")
    private String urlTemplate;

    public PlatformApiAccountBalanceApiResponse executeApi(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {
        HttpEntity response = httpClientService.executeGet(UtilityClass.prepareHttpClientRequestBin(bankAccontParamInputBin, urlTemplate));
        return preparePlatformApiResponse(response);
    }

    private PlatformApiAccountBalanceApiResponse preparePlatformApiResponse(HttpEntity response) throws IOException {
        String resultString = UtilityClass.convertStreamToString(response.getContent());
        return covertObjectInJSON(resultString);
    }

    private PlatformApiAccountBalanceApiResponse covertObjectInJSON(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, PlatformApiAccountBalanceApiResponse.class);
    }
}
