package com.wulai.dictionary;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.dictionary.TermListResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class QueryTermList {
    private int page;
    private int pageSize;


    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }


    public TermListResponse request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/dictionary/term/list", params);
        return wulaiClient.getResponse(httpResponse,TermListResponse.class);



    }
}
