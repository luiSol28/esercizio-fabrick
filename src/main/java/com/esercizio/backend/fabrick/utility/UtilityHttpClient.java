package com.esercizio.backend.fabrick.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.org.apache.xml.internal.utils.URI;
import lombok.experimental.UtilityClass;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class UtilityHttpClient {

    public static String covertObjectInJSON(Object object) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);
        return json;
    }
/*
    public static String prepareUrl(String urlTemplate, List<String> queryParm, ) {
        // URI (URL) parameters
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("planet", "Mars");
        urlParams.put("moon", "Phobos");

// Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlTemplate)
                .queryParam("firstName", "Mark")
                .queryParam("lastName", "Watney");

        builder.buildAndExpand(urlParams).toUri();
    }*
}
