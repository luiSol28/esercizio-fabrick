package com.esercizio.backend.fabrick.service.common;

import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class HttpClientService {

    Logger logger = LoggerFactory.getLogger(HttpClientService.class);

    public HttpEntity executeGet(HttpClientRequestBin httpClientRequestBin) throws IOException, JSONException {

        String url = UtilityClass.buildUri(httpClientRequestBin);
        logger.info("HttpClientService - GET: {}", url);

        final HttpGet httpGet = new HttpGet(url);
        UtilityClass.setHttpRequestHeaders(httpGet, httpClientRequestBin.getHeader());

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









}