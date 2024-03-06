package com.esercizio.backend.fabrick.service.clientRest;

import com.esercizio.backend.fabrick.bin.HttpClientRequestBin;
import org.json.JSONException;

import java.io.IOException;

public interface ClienteRestApi<T,V> {

    T executeApi(V paramApi) throws IOException, JSONException;
}
