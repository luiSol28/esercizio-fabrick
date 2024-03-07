package com.esercizio.backend.fabrick.service.common;

import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import com.esercizio.backend.fabrick.service.platformApi.clientRest.UtilityClassRestClient;
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
        final HttpGet httpGet = new HttpGet(url);
        UtilityClassRestClient.setHttpRequestHeaders(httpGet, httpClientRequestBin.getHeader());
        CloseableHttpClient client = HttpClients.createDefault();
        logger.info("HttpClientService - GET: {}, HEADER: {}", url, httpGet.getAllHeaders());
        CloseableHttpResponse response = client.execute(httpGet);
        logger.info("HttpClientService -  response: {}", response.getEntity());
        return response.getEntity();
    }

    public HttpEntity executePost(HttpClientRequestBin httpClientRequestBin) throws IOException {
        String url = buildUri(httpClientRequestBin);
        final HttpPost httpPost = new HttpPost(url);
        final StringEntity entity = new StringEntity(httpClientRequestBin.getPayload());
        httpPost.setEntity(entity);
        UtilityClassRestClient.setHttpRequestHeaders(httpPost, httpClientRequestBin.getHeader());
        logger.info("HttpClientService - POST: {} PAYLOAD: {} HEADERS: {}", url, httpPost.getEntity(), httpPost.getAllHeaders());
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpPost);
        logger.info("OUTPUT: {}", response.getEntity().getContent());
        return response.getEntity();
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