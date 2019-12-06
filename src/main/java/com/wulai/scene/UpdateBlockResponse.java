package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.Response;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class UpdateBlockResponse {
    private Response response;

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("response", response);
        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/scene/block/response/update", params);
        return defaultClient.getEntityMapFromResponse(httpResponse);

    }


}