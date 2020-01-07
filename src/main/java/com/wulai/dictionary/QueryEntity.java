package com.wulai.dictionary;

import com.WulaiClient;
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

    public Entity request(WulaiClient wulaiClient) throws ClientException, ServerException {
        HashMap<String, Object> params = new HashMap<>();


        params.put("id", id);
        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/dictionary/entity/get", params);
        return wulaiClient.getResponse(httpResponse, Entity.class,"entity");

    }


}
