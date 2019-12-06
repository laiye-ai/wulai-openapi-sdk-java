package com.wulai.dictionary;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.TermItem;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class CreateTerm {
    private TermItem termItem;

    public void setTermItem(TermItem termItem) {
        this.termItem = termItem;
    }

    public TermItem getTermItem() {
        return termItem;
    }


    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("term_item",termItem);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/dictionary/term/create",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);

    }
}
