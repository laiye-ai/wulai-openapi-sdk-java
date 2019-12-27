package com.wulai.user;

import com.WulaiClient;
import com.alibaba.fastjson.annotation.JSONField;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UpdateUser {
    private String userId;
    private String avatarUrl;
    private String nickname;

    @JSONField(name = "user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JSONField(name = "avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JSONField(name = "avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getNickname() {
        return nickname;
    }

    @JSONField(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public int request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("avatar_url", avatarUrl);
        params.put("nickname", nickname);

        CloseableHttpResponse httpResponse = wulaiClient.excuteRequest("/user/update", params);
        return httpResponse.getStatusLine().getStatusCode();

    }
}
