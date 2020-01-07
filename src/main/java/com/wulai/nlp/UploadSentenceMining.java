package com.wulai.nlp;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

public class UploadSentenceMining {

    private List<String> queries;

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }

    public List<String> getQueries() {
        return queries;
    }

    public void request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String ,Object> params=new HashMap<>();
        params.put("queries",queries);
        CloseableHttpResponse httpResponse=wulaiClient.executeRequest("/nlp/sentence/mining/upload",params);
        return;
    }
}
