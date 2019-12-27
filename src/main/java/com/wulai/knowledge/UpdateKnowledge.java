package com.wulai.knowledge;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.Knowledge;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UpdateKnowledge {
    private Knowledge knowledge;

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public Knowledge request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("knowledge", knowledge);
        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/qa/knowledge/update", params);

        return wulaiClient.getResponse(httpResponse,Knowledge.class,"knowledge");


    }
}
