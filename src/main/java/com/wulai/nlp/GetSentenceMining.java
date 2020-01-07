package com.wulai.nlp;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.nlp.SentenceMining;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class GetSentenceMining {
    private int page;
    private int pageSize;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return page;
    }

    public SentenceMining request(WulaiClient wulaiClient)throws ServerException, ClientException {
        HashMap<String ,Object> params =new HashMap<>();
        params.put("page",page);
        params.put("page_size",pageSize);
        CloseableHttpResponse httpResponse=wulaiClient.executeRequest("/nlp/sentence/mining/result/get",params);
        return wulaiClient.getResponse(httpResponse,SentenceMining.class);

    }
}
