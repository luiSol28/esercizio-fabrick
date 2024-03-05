package com.esercizio.backend.fabrick.bin;

import lombok.*;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Data
@Builder
public class HttpClientRequestBin {

    private String urlTemplate;
    private MultiValueMap<String, String> queryParam;
    private Map<String, String> uriParam;
    private Map<String, String> header;
}