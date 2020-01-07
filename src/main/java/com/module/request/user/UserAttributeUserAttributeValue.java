package com.module.request.user;


import com.alibaba.fastjson.annotation.JSONField;

public class UserAttributeUserAttributeValue {

    private UserAttribute userAttribute;
    private UserAttributeValue userAttributeValue;

    @JSONField(name = "user_attribute")
    public UserAttribute getUserAttribute() {
        return userAttribute;
    }

    @JSONField(name = "user_attribute")
    public void setUserAttribute(UserAttribute userAttribute) {
        this.userAttribute = userAttribute;
    }

    @JSONField(name = "user_attribute_value")
    public UserAttributeValue getUserAttributeValue() {
        return userAttributeValue;
    }

    @JSONField(name = "user_attribute_value")
    public void setUserAttributeValue(UserAttributeValue userAttributeValue) {
        this.userAttributeValue = userAttributeValue;
    }
}

