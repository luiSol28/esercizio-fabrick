package com.esercizio.backend.fabrick.service.common;

import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import com.esercizio.backend.fabrick.service.clientRest.UtilityClassRestClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class HttpClientService {

    Logger logger = LoggerFactory.getLogger(HttpClientService.class);

    public HttpEntity executeGet(HttpClientRequestBin httpClientRequestBin) throws IOException {

        String url = buildUri(httpClientRequestBin);
        logger.info("HttpClientService - GET: {}", url);

        final HttpGet httpGet = new HttpGet(url);
        UtilityClassRestClient.setHttpRequestHeaders(httpGet, httpClientRequestBin.getHeader());

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpGet);

        logger.info("HttpClientService -  response: {}", response.getEntity());

        return response.getEntity();

    }

    public CloseableHttpResponse executePost(String URL, String jsonBody) throws IOException {

        final HttpPost httpPost = new HttpPost(URL);
        final StringEntity entity = new StringEntity(jsonBody);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client
                     .execute(httpPost)) {
            return response;
        }
    }


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






}