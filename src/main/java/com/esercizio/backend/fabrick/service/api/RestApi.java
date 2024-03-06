package com.esercizio.backend.fabrick.service.api;

import org.json.JSONException;

import java.io.IOException;

public interface RestApi<T,V> {
    T executeApi(V paramApi) throws IOException, JSONException;
}
