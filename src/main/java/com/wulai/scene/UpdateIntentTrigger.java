package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.IntentTrigger;
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

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("intent_trigger",intent_trigger);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/scene/intent/trigger/update",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);

    }


}
