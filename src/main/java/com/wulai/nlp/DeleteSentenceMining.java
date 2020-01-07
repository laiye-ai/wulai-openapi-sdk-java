package com.wulai.nlp;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class DeleteSentenceMining {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("id",id);
        CloseableHttpResponse httpResponse=wulaiClient.executeRequest("/nlp/sentence/mining/sentence/delete",params);
        return httpResponse.getStatusLine().getStatusCode();
    }
}
