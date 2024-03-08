package com.esercizio.backend.fabrick.service.platformApi.clientRest;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import lombok.experimental.UtilityClass;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


@UtilityClass
public class UtilityClassRestClient {

    Logger logger = LoggerFactory.getLogger(UtilityClassRestClient.class);

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
