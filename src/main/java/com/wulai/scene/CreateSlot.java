package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Slot;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateSlot {
    private Slot slot;

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Slot getSlot() {
        return slot;
    }

    public Slot request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("slot", slot);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/scene/slot/create", params);

        return wulaiClient.getResponse(httpResponse,Slot.class,"slot");

    }


}
