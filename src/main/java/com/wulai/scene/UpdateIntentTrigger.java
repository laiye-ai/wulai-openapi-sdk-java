package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.IntentTrigger;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UpdateIntentTrigger {
    private IntentTrigger intent_trigger;

    public void setIntent_trigger(IntentTrigger intent_trigger) {
        this.intent_trigger = intent_trigger;
    }

    public IntentTrigger getIntent_trigger() {
        return intent_trigger;
    }

    public IntentTrigger request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("intent_trigger", intent_trigger);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/scene/intent/trigger/update", params);

        return wulaiClient.getResponse(httpResponse,IntentTrigger.class,"intent_trigger");
    }


}
