package com.wulai.knowledge;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.knowledge.UserAttributeGroupItemsList;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class QueryUserAttributeGroupItemsList {
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

    public UserAttributeGroupItemsList request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/qa/user-attribute-group-items/list", params);

        return wulaiClient.getResponse(httpResponse, UserAttributeGroupItemsList.class);
    }

}
