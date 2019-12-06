package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class QueryIntentTriggerList {
    private int intent_id;
    private int page;
    private int page_size;

    public void setIntent_id(int intent_id) {
        this.intent_id = intent_id;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getIntent_id() {
        return intent_id;
    }

    public int getPage() {
        return page;
    }

    public int getPage_size() {
        return page_size;
    }


    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("intent_id", intent_id);
        params.put("page", page);
        params.put("page_size", page_size);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/intent/trigger/list", params);
        return defaultClient.getEntityMapFromResponse(httpResponse);

    }
}
