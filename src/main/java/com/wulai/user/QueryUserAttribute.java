package com.wulai.user;

import com.WulaiClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.user.UserAttributeUserAttributeValue;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.List;

public class QueryUserAttribute {
    private String userId;

    @JSONField(name = "user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JSONField(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public List<UserAttributeUserAttributeValue> request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/user/user-attribute/pair/list", params);
        return wulaiClient.getResponseArray(httpResponse, UserAttributeUserAttributeValue.class, "user_attribute_user_attribute_values");
    }
}
