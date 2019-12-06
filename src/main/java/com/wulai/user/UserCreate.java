package com.wulai.user;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UserCreate {
    private String userId;
    private String avatarUrl;
    private String nickname;


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params= new HashMap<>();
        params.put("user_id", userId);
        params.put("avatar_url", avatarUrl);
        params.put("nickname", nickname);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/user/create", params);
        int httpCode = httpResponse.getStatusLine().getStatusCode();

        return httpCode;
    }

}
