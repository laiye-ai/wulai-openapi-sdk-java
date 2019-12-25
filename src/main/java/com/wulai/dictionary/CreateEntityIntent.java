package com.wulai.dictionary;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
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


    public IntentResponse request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("intent_entity", intentEntity);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/dictionary/entity/intent/create", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);
        return JSONObject.parseObject(jsonObject.get("intent_entity").toString(), IntentResponse.class);

    }
}
