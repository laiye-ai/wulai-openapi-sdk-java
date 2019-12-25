package com.wulai.nlp;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class ExtractEntities {
    private String query;

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public JSONObject request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("query", query);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/nlp/entities/extract", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);
        return jsonObject;


    }
}
