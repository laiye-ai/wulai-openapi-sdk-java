package com.wulai.scene;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.IntentTrigger;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateIntentTrigger {
    private IntentTrigger intentTrigger;

    public void setIntentTrigger(IntentTrigger intentTrigger) {
        this.intentTrigger = intentTrigger;
    }

    public IntentTrigger getIntentTrigger() {
        return intentTrigger;
    }


    public IntentTrigger request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("intent_trigger", intentTrigger);
        CloseableHttpResponse closeableHttpResponse = defaultClient.excuteRequest("/scene/intent/trigger/create", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(closeableHttpResponse);
        return JSONObject.parseObject(jsonObject.get("intent_trigger").toString(),IntentTrigger.class);


    }

}
