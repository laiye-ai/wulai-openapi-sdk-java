package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.scene.QueryItem;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

public class QueryIntentTriggerLearningList {
    private int page;
    private int pageSize;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<QueryItem> request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/scene/intent/trigger-learning/list", params);
        return wulaiClient.getResponseArray(httpResponse,QueryItem.class,"query_items");

    }

}
