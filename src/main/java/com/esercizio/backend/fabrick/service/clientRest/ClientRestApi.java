package com.esercizio.backend.fabrick.service.clientRest;

import org.json.JSONException;
import java.io.IOException;

public interface ClientRestApi<T,V> {

    T callApiRest(V paramApi) throws IOException, JSONException;
}
