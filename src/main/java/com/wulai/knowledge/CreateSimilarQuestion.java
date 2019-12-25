package com.wulai.knowledge;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.SimilarQuestion;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateSimilarQuestion {
    private SimilarQuestion similarQuestion;

    public void setSimilarQuestion(SimilarQuestion similarQuestion) {
        this.similarQuestion = similarQuestion;
    }

    public SimilarQuestion getSimilarQuestion() {
        return similarQuestion;
    }

    public SimilarQuestion request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("similar_question", similarQuestion);
        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/qa/similar-question/create", params);
        JSONObject response = defaultClient.getJsonFromResponse(httpResponse);
        return JSONObject.parseObject(response.get("similar_question").toString(), SimilarQuestion.class);
    }

}


