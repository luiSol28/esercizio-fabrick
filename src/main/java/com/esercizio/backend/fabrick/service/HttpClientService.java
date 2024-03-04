package com.esercizio.backend.fabrick.service;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HttpClientService {

    public CloseableHttpResponse executeGet(String URL, String jsonBody) throws IOException {
        final HttpGet httpGet = new HttpGet(URL);
        final StringEntity entity = new StringEntity(jsonBody);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = (CloseableHttpResponse) client
                     .execute(httpGet)) {

            return response;
        }
    }

    public CloseableHttpResponse executePost(String URL, String jsonBody) throws IOException {
        final HttpPost httpPost = new HttpPost(URL);
        final StringEntity entity = new StringEntity(jsonBody);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = (CloseableHttpResponse) client
                     .execute(httpPost)) {

            return response;
        }
    }
}
