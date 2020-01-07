package com.wulai.nlp;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class EmptySentenceMining {
    public int request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/nlp/sentence/mining/empty", params);
        return httpResponse.getStatusLine().getStatusCode();

    }
}
