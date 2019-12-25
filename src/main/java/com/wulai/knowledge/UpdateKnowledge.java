package com.wulai.knowledge;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
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

    public Knowledge request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("knowledge", knowledge);
        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/qa/knowledge/update", params);
        JSONObject response = defaultClient.getJsonFromResponse(httpResponse);

        Knowledge knowledge = JSONObject.parseObject(response.get("knowledge").toString(), Knowledge.class);
        return knowledge;

    }
}
