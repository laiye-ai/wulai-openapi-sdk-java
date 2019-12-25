package com.wulai.dictionary;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryEntityList {
    private int page;
    private int pageSize;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<Map> request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("page", page);
        params.put("page_size", pageSize);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/dictionary/entity/list", params);

        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);

        String object = jsonObject.get("entities").toString();
        List<Map> maps = JSONObject.parseArray(object, Map.class);
        return maps;

    }


}

