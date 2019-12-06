package com.wulai.knowledge;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

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

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();

        params.put("page",page);
        params.put("page_size",pageSize);
        params.put("parent_k_tag_id",parentKTagId);

        CloseableHttpResponse closeableHttpResponse=defaultClient.excuteRequest("/qa/knowledge-tags/list",params);
        return defaultClient.getEntityMapFromResponse(closeableHttpResponse);

    }



}
