package com.esercizio.backend.fabrick.service.platformApi.clientRest;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiExecuteBankTransferApiResponse;
import com.esercizio.backend.fabrick.model.dto.api.BankTransferDto;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiTransactionsApiResponse;
import com.esercizio.backend.fabrick.service.common.HttpClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BankTransferRestClientService implements ClientRestApi<PlatformApiExecuteBankTransferApiResponse, BankAccontParamInputBin> {

    @Autowired
    private HttpClientService httpClientService;

    @Value("${platformapifabrick.moneytransfer.url}")
    private String urlTemplate = "";

    @Value("${platformapifabrick.moneytransfer.mock}")
    private Boolean mock = Boolean.FALSE;

    @Autowired
    private ResourceLoader resourceLoader;

    public PlatformApiExecuteBankTransferApiResponse callApiRest(BankAccontParamInputBin bankAccontParamInputBin) throws IOException, JSONException {
       if (Boolean.FALSE.equals(mock)) {
           HttpEntity response = httpClientService.executePost(prepareHttpClientRequestBin(bankAccontParamInputBin, urlTemplate));
           return preparePlatformApiResponse(response);
       }
       else
           return covertObjectInJSONMock();

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

    private PlatformApiExecuteBankTransferApiResponse covertObjectInJSONMock() throws IOException {
        final Resource fileResource = resourceLoader.getResource("classpath:mock/PlatformApiExecuteBankTransferApiResponseMock.json");
        String jsonString = UtilityClassRestClient.convertStreamToString(fileResource.getInputStream());
        return covertObjectInJSON(jsonString);
    }

    private PlatformApiExecuteBankTransferApiResponse preparePlatformApiResponse(HttpEntity response) throws IOException {
        String resultString = UtilityClassRestClient.convertStreamToString(response.getContent());
        return covertObjectInJSON(resultString);
    }

    private PlatformApiExecuteBankTransferApiResponse covertObjectInJSON(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (StringUtils.isEmpty(jsonString))
            return new PlatformApiExecuteBankTransferApiResponse();
        else
        return objectMapper.readValue(jsonString, PlatformApiExecuteBankTransferApiResponse.class);
    }


}
