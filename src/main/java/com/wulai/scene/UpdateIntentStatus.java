package com.wulai.scene;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Intent;
import com.module.response.scene.IntentStatus;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class UpdateIntentStatus {
    private boolean status;
    private int firstBlockId;
    private int intentId;

    public void setIntentId(int intentId) {
        this.intentId = intentId;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setFirstBlockId(int firstBlockId) {
        this.firstBlockId = firstBlockId;
    }

    public int getIntentId() {
        return intentId;
    }

    public int getFirstBlockId() {
        return firstBlockId;
    }

    public boolean isStatus() {
        return status;
    }

    public IntentStatus request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("first_block_id", firstBlockId);
        params.put("intent_id", intentId);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/intent/status/update", params);
        return defaultClient.getResponse(httpResponse, IntentStatus.class);

    }


}
