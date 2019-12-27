package com.wulai.scene;

import com.WulaiClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.IntentTrigger;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

public class QueryIntentTriggerList {
    private int intentId;
    private int page;
    private int page_size;

    @JSONField(name = "intent_id")
    public void setIntentId(int intentId) {
        this.intentId = intentId;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    @JSONField(name = "intent_id")
    public int getIntentId() {
        return intentId;
    }

    public int getPage() {
        return page;
    }

    public int getPage_size() {
        return page_size;
    }


    public List<IntentTrigger> request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("intent_id", intentId);
        params.put("page", page);
        params.put("page_size", page_size);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/scene/intent/trigger/list", params);

        return wulaiClient.getResponseArray(httpResponse,IntentTrigger.class,"intent_triggers");


    }
}
