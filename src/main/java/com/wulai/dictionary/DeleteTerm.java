package com.wulai.dictionary;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class DeleteTerm {
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

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/dictionary/term/delete", params);

        return httpResponse.getStatusLine().getStatusCode();
    }

}
