package com.wulai.knowledge;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.Filter;
import com.module.response.knowledge.UserAttributeGroupAnswersList;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class QueryUserAttributeGroupAnswersList {
    private int page;
    private int pageSize;
    private Filter filter = new Filter();

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Filter getFilter() {
        return filter;
    }

    public UserAttributeGroupAnswersList request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);
        params.put("filter", filter);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/qa/user-attribute-group-answers/list", params);

        return wulaiClient.getResponse(httpResponse, UserAttributeGroupAnswersList.class);
    }


}
