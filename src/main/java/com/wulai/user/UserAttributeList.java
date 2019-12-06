package com.wulai.user;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.user.UserAttributeListRequest;
import com.module.response.user.UserAttributeListResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class UserAttributeList {
    private UserAttributeListRequest userAttributeListRequest;

    public void setUserAttributeListRequest(UserAttributeListRequest userAttributeListRequest) {
        this.userAttributeListRequest = userAttributeListRequest;
    }

    public UserAttributeListRequest getUserAttributeListRequest() {
        return userAttributeListRequest;
    }

    public UserAttributeListResponse request(DefaultClient defaultClient) throws ClientException, ServerException {
        HashMap<String,Object> params= new HashMap<>();
        HashMap<String, Object> useInUserAttributeGroup = new HashMap<>();
        useInUserAttributeGroup.put("use_in_user_attribute_group", userAttributeListRequest.getFilter());
        params.put("page", userAttributeListRequest.getPage());
        params.put("page_size", userAttributeListRequest.getPageSize());
        params.put("filter", useInUserAttributeGroup);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/user-attribute/list", params);
        Map map = null;
        map = defaultClient.getEntityMapFromResponse(httpResponse);

        return new UserAttributeListResponse(map);
    }
}
