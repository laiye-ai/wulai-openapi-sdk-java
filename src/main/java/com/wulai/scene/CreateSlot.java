package com.wulai.scene;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Slot;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class CreateSlot {
    private Slot slot;

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Slot getSlot() {
        return slot;
    }

    public Slot request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("slot", slot);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/slot/create", params);
        Map map=defaultClient.getEntityMapFromResponse(httpResponse);
        JSONObject jsonObject=JSONObject.parseObject(map.get("slot").toString());
        Slot returnslot=new Slot();
        returnslot.setId(Integer.valueOf(jsonObject.get("id").toString()));
        returnslot.setQuery_slot_filling(Boolean.valueOf(jsonObject.get("query_slot_filling").toString()));
        returnslot.setName(jsonObject.get("name").toString());
        returnslot.setScene_id(Integer.valueOf(jsonObject.get("scene_id").toString()));
        return returnslot;
    }


}
