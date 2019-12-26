package com.wulai.scene;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.scene.DataSource;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

public class QuerySlotDataSourceList {
    private int slotId;

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getSlotId() {
        return slotId;
    }

    public List<DataSource> request(DefaultClient defaultClient) throws ServerException, ClientException {

        HashMap<String, Object> params = new HashMap<>();

        params.put("slot_id", slotId);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/slot/data-source/list", params);
        return defaultClient.getResponseArray(httpResponse,DataSource.class,"data_sources");

    }

}
