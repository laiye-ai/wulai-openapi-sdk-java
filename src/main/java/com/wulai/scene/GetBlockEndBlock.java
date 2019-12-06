package com.wulai.scene;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class GetBlockEndBlock {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {

        HashMap<String,Object> params=new HashMap<>();
        params.put("id",id);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/scene/block/end-block/get",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);

    }

}
