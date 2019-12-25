package com.wulai.dictionary;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.dictionary.Entity;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class QueryEntity {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Entity request(DefaultClient defaultClient) throws ClientException, ServerException {
        HashMap<String, Object> params = new HashMap<>();


        params.put("id", id);
        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/dictionary/entity/get", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);
        Entity entity = JSONObject.parseObject(jsonObject.get("entity").toString(), Entity.class);
        return entity;
    }


}
