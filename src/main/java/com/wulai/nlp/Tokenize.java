package com.wulai.nlp;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class Tokenize {
    private String query;

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();

        params.put("query",query);
        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/nlp/tokenize",params);

        return defaultClient.getEntityMapFromResponse(httpResponse);

    }

}
