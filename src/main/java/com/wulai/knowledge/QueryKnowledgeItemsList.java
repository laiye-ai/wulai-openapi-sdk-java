package com.wulai.knowledge;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.knowledge.KnowledgeItemsList;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class QueryKnowledgeItemsList {
    private int page;
    private int pageSize;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public KnowledgeItemsList request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/qa/knowledge-items/list", params);
        return wulaiClient.getResponse(httpResponse, KnowledgeItemsList.class);

    }
}
