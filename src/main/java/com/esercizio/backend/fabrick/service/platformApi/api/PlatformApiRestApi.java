package com.esercizio.backend.fabrick.service.platformApi.api;

import org.json.JSONException;

import java.io.IOException;

public interface PlatformApiRestApi<T,V> {
    T executeApi(V paramApi) throws IOException, JSONException;
}
