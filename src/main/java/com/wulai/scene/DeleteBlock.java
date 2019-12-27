package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class DeleteBlock {
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("id", id);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/scene/block/delete", params);
        return httpResponse.getStatusLine().getStatusCode();

    }

}
