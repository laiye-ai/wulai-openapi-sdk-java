package com.wulai.knowledge;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.SimilarQuestion;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UpdateSimilarQuestion {
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

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/qa/similar-question/update", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);
        String response = jsonObject.get("similar_question").toString();

        SimilarQuestion similarQuestion = JSONObject.parseObject(response, SimilarQuestion.class);
        return similarQuestion;
    }
}
