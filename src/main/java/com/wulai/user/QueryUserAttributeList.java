package com.wulai.user;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.Filter;
import com.module.request.user.UserAttributeUserAttributeValue;
import com.module.response.user.UserAttributeList;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

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

    public UserAttributeList request(DefaultClient defaultClient) throws ClientException, ServerException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("pageSize", pageSize);
        params.put("filter", filter);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/user-attribute/list", params);
        JSONObject jsonObject = defaultClient.getJsonFromResponse(httpResponse);
        UserAttributeList userAttributeList = new UserAttributeList();
        userAttributeList.setPageCount(Integer.parseInt(jsonObject.get("page_count").toString()));
        List<UserAttributeUserAttributeValue> userAttributeUserAttributeValues = JSONObject.parseArray(jsonObject.get("user_attribute_user_attribute_values").toString(), UserAttributeUserAttributeValue.class);
        userAttributeList.setUserAttributeUserAttributeValues(userAttributeUserAttributeValues);
        return userAttributeList;
    }
}
