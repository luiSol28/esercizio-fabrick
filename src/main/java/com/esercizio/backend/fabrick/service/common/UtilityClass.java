package com.esercizio.backend.fabrick.service.common;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@lombok.experimental.UtilityClass
public class UtilityClass {

    Logger logger = LoggerFactory.getLogger(UtilityClass.class);

    public String buildUri(HttpClientRequestBin httpClientRequestBin) {

        URI uriResult = URI.create("");
        if (httpClientRequestBin != null) {
            String urlTemplate = httpClientRequestBin.getUrlTemplate();
            Map<String, String> uriParam = httpClientRequestBin.getUriParam();
            MultiValueMap<String, String> queryParam = httpClientRequestBin.getQueryParam();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlTemplate);
            if (queryParam != null) {
                builder.queryParams(queryParam);
            }
            builder.uriVariables(new HashMap(uriParam));
            uriResult = builder.build().toUri();
            logger.info("HttpClientService - composed URI: {}", uriResult);
        }
        return uriResult.toString();
    }

    public static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public HttpClientRequestBin prepareHttpClientRequestBin(BankAccontParamInputBin bankAccontParamInputBin, String urlTemplate) {
        return HttpClientRequestBin.builder()
                .urlTemplate(urlTemplate)
                .uriParam(prepareUriParamMap(bankAccontParamInputBin))
                .header(prepareHeaderMap(bankAccontParamInputBin))
                .build();
    }

    public Map<String, String> prepareUriParamMap(BankAccontParamInputBin bankAccontParamInputBin) {
        Map<String, String> uriParam = new HashMap<>();
        uriParam.put("accountId", bankAccontParamInputBin.getIdAccount());
        return uriParam;
    }

    public Map<String, String> prepareHeaderMap(BankAccontParamInputBin bankAccontParamInputBin) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", bankAccontParamInputBin.getContentType());
        headerMap.put("Auth-Schema", bankAccontParamInputBin.getAuthSchema());
        headerMap.put("Api-Key", bankAccontParamInputBin.getApiKey());
        return headerMap;
    }

    public void setHttpRequestHeaders(HttpRequestBase httpRequestBase, Map<String, String> header) {
        header.keySet().forEach(
                key -> httpRequestBase.setHeader(key,header.get(key))
        );
    }
}
