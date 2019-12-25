package com.module.response.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.user.UserAttributeUserAttributeValue;

import java.util.List;

public class UserAttributeList {
    private int pageCount;
    private List<UserAttributeUserAttributeValue> userAttributeUserAttributeValues;

    @JSONField(name = "user_attribute_user_attribute_values")
    public void setUserAttributeUserAttributeValues(List<UserAttributeUserAttributeValue> userAttributeUserAttributeValues) {
        this.userAttributeUserAttributeValues = userAttributeUserAttributeValues;
    }

    @JSONField(name = "page_count")
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @JSONField(name = "page_count")
    public int getPageCount() {
        return pageCount;
    }

    @JSONField(name = "user_attribute_user_attribute_values")
    public List<UserAttributeUserAttributeValue> getUserAttributeUserAttributeValues() {
        return userAttributeUserAttributeValues;
    }
}
