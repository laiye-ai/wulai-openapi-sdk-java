package com.wulai.scene;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.IntentTrigger;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class UpdateIntentTrigger {
    private IntentTrigger intent_trigger;

    public void setIntent_trigger(IntentTrigger intent_trigger) {
        this.intent_trigger = intent_trigger;
    }

    public IntentTrigger getIntent_trigger() {
        return intent_trigger;
    }

    public IntentTrigger request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("intent_trigger", intent_trigger);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/intent/trigger/update", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);
        return JSONObject.parseObject(jsonObject.get("intent_trigger").toString(),IntentTrigger.class);

    }


}
