package com.wulai.scene;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.scene.Response;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateBlockResponse {
    private Response response;

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public Response request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("dictionary", response);
        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/scene/block/response/create", params);

        return wulaiClient.getResponse(httpResponse, Response.class,"response");


    }

}
