package com.wulai.scene;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Intent;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UpdateIntent {
    private Intent intent;

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }

    public Intent request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("intent", intent);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/intent/update", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);
        return JSONObject.parseObject(jsonObject.get("intent").toString(), Intent.class);

    }

}
