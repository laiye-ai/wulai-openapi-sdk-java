package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Intent;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class CreateIntent {
    private Intent intent;

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object > params= new HashMap<>();
        params.put("intent",intent);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/scene/intent/create",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);

    }
}
