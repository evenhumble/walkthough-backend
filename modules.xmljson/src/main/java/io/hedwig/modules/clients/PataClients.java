package io.hedwig.modules.clients;

import okhttp3.*;

import java.io.IOException;

public class PataClients {

    private final static String ERROR_RESPONSE = "{\"data\":\"error\"}";
    public static String getNewPetaResponse(String url,String body){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType,
            body);
        Request request = new Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return ERROR_RESPONSE;
        }
    }

    public String getOldPetaResponse(){
        throw new RuntimeException("Not Implemented Yet");
    }
}
