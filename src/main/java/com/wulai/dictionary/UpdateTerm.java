package com.wulai.dictionary;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.dictionary.TermItem;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UpdateTerm {
    private TermItem termItem;


    public void setTermItem(TermItem termItem) {
        this.termItem = termItem;
    }

    public TermItem getTermItem() {
        return termItem;
    }

    public TermItem request(WulaiClient wulaiClient) throws ServerException, ClientException {

        HashMap<String, Object> params = new HashMap<>();
        params.put("term_item", termItem);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/dictionary/term/update", params);

        return wulaiClient.getResponse(httpResponse,TermItem.class,"term_item");


    }


}
