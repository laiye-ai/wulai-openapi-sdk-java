package com.wulai.scene;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Scene;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuerySceneList {

    public List<Scene> request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/list", params);

        return defaultClient.getResponseArray(httpResponse,Scene.class,"scenes");

    }
}
