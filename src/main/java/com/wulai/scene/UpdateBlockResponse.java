package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Response;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UpdateBlockResponse {
    private Response response;

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public Response request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("response", response);
        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/block/response/update", params);

        return defaultClient.getResponse(httpResponse,Response.class,"response");
    }
}
