package com.wulai.user;

import com.DefaultClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.response.user.UserInfo;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class QueryUserInfo {
    private String userId;

    @JSONField(name = "user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JSONField(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public UserInfo request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/user/get", params);


        return defaultClient.getResponse(httpResponse,UserInfo.class);
    }


}
