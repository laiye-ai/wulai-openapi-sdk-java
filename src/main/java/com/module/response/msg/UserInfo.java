package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

public class UserInfo {
    private String avatarURL;
    private String nickname;

    @JSONField(name = "avatar_url")
    public String getAvatarURL() {
        return avatarURL;
    }

    @JSONField(name = "avatar_url")
    public void setAvatarURL(String value) {
        this.avatarURL = value;
    }

    @JSONField(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    @JSONField(name = "nickname")
    public void setNickname(String value) {
        this.nickname = value;
    }
}
