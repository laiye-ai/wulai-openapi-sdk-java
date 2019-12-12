package com.module.request.knowledge;

import com.module.request.user.UserAttributeUserAttributeValue;

import java.util.ArrayList;
import java.util.List;

public class UserAttributeGroupItem {
    private List<UserAttributeUserAttributeValue> user_attribute_user_attribute_value =new ArrayList<>();
    private UserAttributeGroup user_attribute_group =new UserAttributeGroup();

    public void setUser_attribute_group(UserAttributeGroup userAttributeGroup ){
        this.user_attribute_group=userAttributeGroup;
    }

    public List<UserAttributeUserAttributeValue> getUser_attribute_user_attribute_value() {
        return user_attribute_user_attribute_value;
    }

    public UserAttributeGroup getUser_attribute_group() {
        return user_attribute_group;
    }

    public void addUserAttributeUserAttributeValue(UserAttributeUserAttributeValue userAttributeUserAttributeValue){
        user_attribute_user_attribute_value.add(userAttributeUserAttributeValue);
    }

}

