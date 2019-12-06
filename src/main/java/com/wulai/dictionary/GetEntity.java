package com.wulai.dictionary;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class GetEntity {
    private int id ;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Map request(DefaultClient defaultClient) throws ClientException, ServerException {
        HashMap<String,Object> params=new HashMap<>();


        params.put("id",id);
        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/dictionary/entity/get",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);


    }


}
