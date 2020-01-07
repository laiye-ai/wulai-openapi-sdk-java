package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Scene;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

public class QuerySceneList {

    public List<Scene> request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/scene/list", params);

        return wulaiClient.getResponseArray(httpResponse,Scene.class,"scenes");

    }
}
