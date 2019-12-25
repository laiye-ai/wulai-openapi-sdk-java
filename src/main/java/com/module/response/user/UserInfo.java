package com.module.response.user;

import com.alibaba.fastjson.annotation.JSONField;

public class UserInfo {
    private String avatarUrl;
    private String nickname;


    @JSONField(name = "avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    @JSONField(name = "avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }
}
