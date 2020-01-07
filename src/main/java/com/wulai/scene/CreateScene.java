package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Scene;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;


//创建场景
public class CreateScene {
    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public Scene request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("scene", scene);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/scene/create", params);

        return wulaiClient.getResponse(httpResponse,Scene.class,"scene");
    }

}
