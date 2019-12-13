package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Slot;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class UpdateSlot {
    private Slot slot;

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Slot getSlot() {
        return slot;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("slot", slot);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/slot/update", params);
        return defaultClient.getEntityMapFromResponse(httpResponse);


    }


}
