package com.wulai.user;

import com.WulaiClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.Filter;
import com.module.response.user.UserAttributeList;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class QueryUserAttributeList {
    private int page;
    private int pageSize;
    private Filter filter;

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Filter getFilter() {
        return filter;
    }

    public int getPage() {
        return page;
    }

    @JSONField(name = "page_size")
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @JSONField(name = "page_size")
    public int getPageSize() {
        return pageSize;
    }

    public UserAttributeList request(WulaiClient wulaiClient) throws ClientException, ServerException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("pageSize", pageSize);
        params.put("filter", filter);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/user-attribute/list", params);
        return wulaiClient.getResponse(httpResponse,UserAttributeList.class);
    }
}
