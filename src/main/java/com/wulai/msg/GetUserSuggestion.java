package com.wulai.msg;

import com.WulaiClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

public class GetUserSuggestion {

    private String query;
    private String userId;


    @JSONField(name = "user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @JSONField(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public String getQuery() {
        return query;
    }

    public List<String> request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("query", query);
        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/msg/user-suggestion/get", params);
        return wulaiClient.getResponseArray(httpResponse,String.class,"user_suggestions");

    }
}
