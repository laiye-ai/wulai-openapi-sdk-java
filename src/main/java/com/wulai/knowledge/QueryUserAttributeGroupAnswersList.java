package com.wulai.knowledge;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.Filter;
import com.module.request.knowledge.UserAttributeGroupAnswer;
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

    public UserAttributeGroupAnswersList request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("page_size", pageSize);
        params.put("filter", filter);

        CloseableHttpResponse closeableHttpResponse = defaultClient.excuteRequest("/qa/user-attribute-group-answers/list", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(closeableHttpResponse);
        UserAttributeGroupAnswersList userAttributeGroupAnswersList = new UserAttributeGroupAnswersList();
        userAttributeGroupAnswersList.setPageCount(Integer.parseInt(jsonObject.get("page_count").toString()));
        userAttributeGroupAnswersList.setUser_attribute_group_answers(JSONObject.parseArray(jsonObject.get("user_attribute_group_answers").toString(), UserAttributeGroupAnswer.class));
        return userAttributeGroupAnswersList;
    }


}
