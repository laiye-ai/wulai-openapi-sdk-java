package com.wulai.dictionary;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.dictionary.TermItem;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateTerm {
    private TermItem termItem;

    public void setTermItem(TermItem termItem) {
        this.termItem = termItem;
    }

    public TermItem getTermItem() {
        return termItem;
    }


    public TermItem request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("term_item", termItem);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/dictionary/term/create", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);
        return JSONObject.parseObject(jsonObject.get("term_item").toString(), TermItem.class);

    }
}
