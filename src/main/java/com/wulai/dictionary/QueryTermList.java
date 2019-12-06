package com.wulai.dictionary;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

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


    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();
        params.put("page",page);
        params.put("page_size",pageSize);

        CloseableHttpResponse httpResponse=defaultClient.excuteRequest("/dictionary/term/list",params);
        return defaultClient.getEntityMapFromResponse(httpResponse);


    }
}
