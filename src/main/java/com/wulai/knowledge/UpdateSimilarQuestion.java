package com.wulai.knowledge;

import com.WulaiClient;
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


    public SimilarQuestion request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("similar_question", similarQuestion);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/qa/similar-question/update", params);

        return wulaiClient.getResponse(httpResponse, SimilarQuestion.class, "similar_question");
    }
}
