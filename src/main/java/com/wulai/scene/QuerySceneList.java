package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class QuerySceneList {

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/list", params);
        return defaultClient.getEntityMapFromResponse(httpResponse);

    }

}
