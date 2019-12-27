package com.wulai.user;

import com.WulaiClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.user.UserAttributeUserAttributeValue;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateUserAttribute {
    private String userId;

    private List<UserAttributeUserAttributeValue> userAttributeUserAttributeValues =
            new ArrayList<>();

    @JSONField(name = "user_attribute_user_attribute_values")
    public void setUserAttributeUserAttributeValues(List<UserAttributeUserAttributeValue> userAttributeUserAttributeValues) {
        this.userAttributeUserAttributeValues = userAttributeUserAttributeValues;
    }

    @JSONField(name = "user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JSONField(name = "user_attribute_user_attribute_values")
    public List<UserAttributeUserAttributeValue> getUserAttributeUserAttributeValues() {
        return userAttributeUserAttributeValues;
    }

    @JSONField(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void addUserAttributeUserAttributeValue(UserAttributeUserAttributeValue userAttributeUserAttributeValue) {
        this.userAttributeUserAttributeValues.add(userAttributeUserAttributeValue);
    }

    public int request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("user_id", userId);
        params.put("user_attribute_user_attribute_value", userAttributeUserAttributeValues);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/user/user-attribute/create", params);
        return httpResponse.getStatusLine().getStatusCode();

    }
}
