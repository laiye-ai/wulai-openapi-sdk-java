package com.wulai.scene;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Intent;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryIntentList {
    private int scene_id;

    public void setScene_id(int scene_id) {
        this.scene_id = scene_id;
    }

    public int getScene_id() {
        return scene_id;
    }

    public List<Intent> request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("scene_id", scene_id);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/intent/list", params);

       // return defaultClient.getResponse(httpResponse,Intent.class,"intents");
        return defaultClient.getResponseArray(httpResponse,Intent.class,"intents");

    }

}
