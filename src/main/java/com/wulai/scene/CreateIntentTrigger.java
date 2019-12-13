package com.wulai.scene;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.IntentTrigger;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class CreateIntentTrigger {
    private IntentTrigger intentTrigger;

    public void setIntentTrigger(IntentTrigger intentTrigger) {
        this.intentTrigger = intentTrigger;
    }

    public IntentTrigger getIntentTrigger() {
        return intentTrigger;
    }


    public IntentTrigger request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("intent_trigger",intentTrigger);
        CloseableHttpResponse closeableHttpResponse=defaultClient.excuteRequest("/scene/intent/trigger/create",params);
        Map map= defaultClient.getEntityMapFromResponse(closeableHttpResponse);
        IntentTrigger intentTrigger=new IntentTrigger();
        JSONObject jsonObject=JSONObject.parseObject(map.get("intent_trigger").toString());
        intentTrigger.setType(jsonObject.get("type").toString());
        intentTrigger.setIntent_id(Integer.valueOf(jsonObject.get("intent_id").toString()));
        intentTrigger.setText(jsonObject.get("text").toString());
        intentTrigger.setId(Integer.valueOf(jsonObject.get("id").toString()));
        return intentTrigger;
    }

}
