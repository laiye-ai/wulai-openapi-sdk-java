package com.wulai.nlp;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.nlp.EntityElement;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

public class ExtractEntities {
    private String query;

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public List<EntityElement> request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("query", query);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/nlp/entities/extract", params);
        return wulaiClient.getResponseArray(httpResponse, EntityElement.class,"entities");

    }
}
