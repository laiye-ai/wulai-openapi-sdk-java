package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Intent;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;


//创建意图
public class CreateIntent {
    private Intent intent;

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }

    public Intent request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("intent", intent);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/scene/intent/create", params);
        return wulaiClient.getResponse(httpResponse,Intent.class,"intent");


    }
}
