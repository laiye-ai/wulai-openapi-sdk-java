package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Slot;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class GetSlot {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Slot request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("id", id);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/scene/slot/get", params);

        return wulaiClient.getResponse(httpResponse,Slot.class,"slot");
    }
}
