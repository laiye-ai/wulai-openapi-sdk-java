package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class UpdateIntentStatus {
    private boolean status;
    private int first_block_id;
    private int intent_id;

    public void setIntent_id(int intent_id) {
        this.intent_id = intent_id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setFirst_block_id(int first_block_id) {
        this.first_block_id = first_block_id;
    }

    public int getIntent_id() {
        return intent_id;
    }

    public int getFirst_block_id() {
        return first_block_id;
    }

    public boolean isStatus() {
        return status;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("first_block_id", first_block_id);
        params.put("intent_id", intent_id);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/intent/status/update", params);
        return defaultClient.getEntityMapFromResponse(httpResponse);


    }


}
