package com.wulai.knowledge;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QuerySimilarQuestionList {
    private String knowledgeId;
    private int page;
    private int pageSize;
    private String similarQuestionId;

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSimilarQuestionId(String similarQuestionId) {
        this.similarQuestionId = similarQuestionId;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException{
        HashMap<String,Object> params=new HashMap<String, Object>();
        params.put("knowledge_id",knowledgeId);
        params.put("page",page);
        params.put("page_size",pageSize);
        params.put("similar_question_id",similarQuestionId);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/qa/similar-question/list", params);
        return defaultClient.getEntityMapFromResponse(httpResponse);
    }




}
