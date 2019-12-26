package com.wulai.knowledge;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.knowledge.KnowledgeTag;
import com.module.response.knowledge.KnowledgeTagsList;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class QueryKnowledgeTagsList {
    private int page;
    private int pageSize;
    private String parentKTagId;

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setParentKTagId(String parentKTagId) {
        this.parentKTagId = parentKTagId;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getParentKTagId() {
        return parentKTagId;
    }

    public KnowledgeTagsList request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("page", page);
        params.put("page_size", pageSize);
        params.put("parent_k_tag_id", parentKTagId);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/qa/knowledge-tags/list", params);
        return defaultClient.getResponse(httpResponse,KnowledgeTagsList.class);


    }


}
