package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class QuerySlotDataSourceList {
    private int slot_id;

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {

        HashMap<String, Object> params = new HashMap<>();

        params.put("slot_id", slot_id);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/slot/data-source/list", params);
        return defaultClient.getEntityMapFromResponse(httpResponse);


    }

}
