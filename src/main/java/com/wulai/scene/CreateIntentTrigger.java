package com.wulai.scene;

import com.WulaiClient;
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


    public IntentTrigger request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("intent_trigger", intentTrigger);
        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/scene/intent/trigger/create", params);
        return wulaiClient.getResponse(httpResponse, IntentTrigger.class,"intent_trigger");



    }

}
