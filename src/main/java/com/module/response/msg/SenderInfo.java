package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

public class SenderInfo {
    private String avatarURL;
    private String nickname;
    private String realName;

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

    @JSONField(name = "real_name")
    public String getRealName() {
        return realName;
    }

    @JSONField(name = "real_name")
    public void setRealName(String value) {
        this.realName = value;
    }
}
