package com.wulai.knowledge;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.knowledge.KnowledgeItems;
import com.module.response.knowledge.KnowledgeItemsList;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

public class QueryKnowledgeItemsList {
    private int page;
    private int pageSize;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public KnowledgeItemsList request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/qa/knowledge-items/list", params);
        JSONObject response = defaultClient.getJsonFromResponse(httpResponse);
        KnowledgeItemsList knowledgeItemsList = new KnowledgeItemsList();
        knowledgeItemsList.setPageCount(Integer.parseInt(response.get("page_count").toString()));
        List<KnowledgeItems> knowledgeItems = JSONObject.parseArray(response.get("knowledge_items").toString(), KnowledgeItems.class);
        knowledgeItemsList.setKnowledgeItems(knowledgeItems);
        return knowledgeItemsList;

    }
}
