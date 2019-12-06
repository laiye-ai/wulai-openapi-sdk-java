package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class DeleteSlotDataSource {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/slot/data-source/delete", params);

        return httpResponse.getStatusLine().getStatusCode();

    }


}