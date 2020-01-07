package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.scene.IntentStatus;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

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

    public IntentStatus request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("first_block_id", firstBlockId);
        params.put("intent_id", intentId);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/scene/intent/status/update", params);
        return wulaiClient.getResponse(httpResponse, IntentStatus.class);

    }


}
