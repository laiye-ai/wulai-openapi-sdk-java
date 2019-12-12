package com.wulai.knowledge;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.SimilarQuestion;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class UpdateSimilarQuestion {
    private SimilarQuestion similarQuestion;

    public void setSimilarQuestion(SimilarQuestion similarQuestion) {
        this.similarQuestion = similarQuestion;
    }

    public SimilarQuestion getSimilarQuestion() {
        return similarQuestion;
    }


    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("similar_question",similarQuestion);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/qa/similar-question/update",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);

    }
}
