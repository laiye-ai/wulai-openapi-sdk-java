package com.wulai.dictionary;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.dictionary.IntentEntity;
import com.module.response.dictionary.IntentResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateEntityIntent {
    private IntentEntity intentEntity;


    public void setIntentEntity(IntentEntity intentEntity) {
        this.intentEntity = intentEntity;
    }

    public IntentEntity getIntentEntity() {
        return intentEntity;
    }


    public IntentResponse request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("intent_entity", intentEntity);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/dictionary/entity/intent/create", params);

        return wulaiClient.getResponse(httpResponse,IntentResponse.class,"intent_entity");

    }
}
