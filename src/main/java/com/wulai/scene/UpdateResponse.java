package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Response;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UpdateResponse {
    private Response response;

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public Response request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("response", response);
        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/scene/block/response/update", params);

        return wulaiClient.getResponse(httpResponse,Response.class,"response");
    }
}
