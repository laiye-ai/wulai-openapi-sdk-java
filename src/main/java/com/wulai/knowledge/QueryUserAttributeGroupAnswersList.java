package com.wulai.knowledge;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class QueryUserAttributeGroupAnswersList {
    private int page;
    private int pageSize;
    private Filter filter=new Filter();

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setFilterKnowledgeId(String knowledgeId) {
        filter.setKnowledgeId(knowledgeId);
    }

    public void setFilterUserAttributeGroupId(String userAttributeGroupId) {
        filter.setUserAttributeGroupId(userAttributeGroupId);
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("page",page);
        params.put("page_size",pageSize);
        params.put("filter",filter);

        CloseableHttpResponse closeableHttpResponse=defaultClient.excuteRequest("/qa/user-attribute-group-answers/list",params);
        return defaultClient.getEntityMapFromResponse(closeableHttpResponse);

    }


}
class Filter{
    String knowledgeId;
    String userAttributeGroupId;

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setUserAttributeGroupId(String userAttributeGroupId) {
        this.userAttributeGroupId = userAttributeGroupId;
    }

    public String getUserAttributeGroupId() {
        return userAttributeGroupId;
    }
}