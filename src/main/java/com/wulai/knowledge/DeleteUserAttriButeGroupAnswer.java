package com.wulai.knowledge;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class DeleteUserAttriButeGroupAnswer {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);

        CloseableHttpResponse closeableHttpResponse = wulaiClient.executeRequest("/qa/user-attribute-group-answer/delete", params);
        return closeableHttpResponse.getStatusLine().getStatusCode();

    }
}
