package com.module.request.knowledge;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.user.UserAttributeUserAttributeValue;

import java.util.ArrayList;
import java.util.List;

public class UserAttributeGroupItem {
    private List<UserAttributeUserAttributeValue> userAttributeUserAttributeValues = new ArrayList<>();
    private UserAttributeGroup userAttributeGroup = new UserAttributeGroup();

    @JSONField(name = "user_attribute_group")
    public void setUserAttributeGroup(UserAttributeGroup userAttributeGroup) {
        this.userAttributeGroup = userAttributeGroup;
    }

    @JSONField(name = "user_attribute_user_attribute_value")
    public List<UserAttributeUserAttributeValue> getUserAttributeUserAttributeValues() {
        return userAttributeUserAttributeValues;
    }

    @JSONField(name = "user_attribute_user_attribute_value")
    public void setUserAttributeUserAttributeValues(List<UserAttributeUserAttributeValue> userAttributeUserAttributeValues) {
        this.userAttributeUserAttributeValues = userAttributeUserAttributeValues;
    }

    @JSONField(name = "user_attribute_group")
    public UserAttributeGroup getUserAttributeGroup() {
        return userAttributeGroup;
    }

    public void addUserAttributeUserAttributeValue(UserAttributeUserAttributeValue userAttributeUserAttributeValue) {
        userAttributeUserAttributeValues.add(userAttributeUserAttributeValue);
    }

}

