package com.wulai.user;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.user.UserAttributeCreateRequest;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UserAttributeCreate {
    private UserAttributeCreateRequest userAttributeCreateRequest;

    public void setUserAttributeCreateRequest(UserAttributeCreateRequest userAttributeCreateRequest) {
        this.userAttributeCreateRequest = userAttributeCreateRequest;
    }

    public UserAttributeCreateRequest getUserAttributeCreateRequest() {
        return userAttributeCreateRequest;
    }

    public int request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params= new HashMap<>();

        params.put("user_id", userAttributeCreateRequest.getUser_id());
        params.put("user_attribute_user_attribute_value",
                userAttributeCreateRequest.getUser_attribute_user_attribute_value());

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/user/user-attribute/create", params);
        int httpCode = httpResponse.getStatusLine().getStatusCode();

        return httpCode;
    }
}
