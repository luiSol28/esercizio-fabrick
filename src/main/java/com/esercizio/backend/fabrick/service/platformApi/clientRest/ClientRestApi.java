package com.esercizio.backend.fabrick.service.platformApi.clientRest;

import com.esercizio.backend.fabrick.bin.PlatformApiParamInput;
import com.esercizio.backend.fabrick.model.platformApi.PlatformApiResponse;
import org.json.JSONException;
import java.io.IOException;

public interface ClientRestApi<T extends PlatformApiResponse, V extends PlatformApiParamInput> {

    T callApiRest(V paramApi) throws IOException, JSONException;
}
