package com.esercizio.backend.fabrick.service.common;

import com.esercizio.backend.fabrick.bin.BankAccontParamInputBin;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


@lombok.experimental.UtilityClass
public class UtilityClass {

    Logger logger = LoggerFactory.getLogger(UtilityClass.class);

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
