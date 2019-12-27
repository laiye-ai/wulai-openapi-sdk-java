package com.wulai.scene;

import com.WulaiClient;
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

    public List<DataSource> request(WulaiClient wulaiClient) throws ServerException, ClientException {

        HashMap<String, Object> params = new HashMap<>();

        params.put("slot_id", slotId);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/scene/slot/data-source/list", params);
        return wulaiClient.getResponseArray(httpResponse,DataSource.class,"data_sources");

    }

}
