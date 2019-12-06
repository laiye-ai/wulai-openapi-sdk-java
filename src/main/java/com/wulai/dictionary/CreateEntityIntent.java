package com.wulai.dictionary;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.IntentEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class CreateEntityIntent {
    private IntentEntity intentEntity;


    public void setIntentEntity(IntentEntity intentEntity) {
        this.intentEntity = intentEntity;
    }

    public IntentEntity getIntentEntity() {
        return intentEntity;
    }


    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params =new HashMap<>();
        params.put("intent_entity",intentEntity);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/dictionary/entity/intent/create",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);
    }
}
