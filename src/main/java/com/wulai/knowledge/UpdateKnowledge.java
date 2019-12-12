package com.wulai.knowledge;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.Knowledge;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class UpdateKnowledge {
    private Knowledge knowledge;

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("knowledge",knowledge);
        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/qa/knowledge/update",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);
    }
}
