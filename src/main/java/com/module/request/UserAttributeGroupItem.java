package com.module.request;

import com.module.request.user.UserAttributeUserAttributeValue;

import java.util.ArrayList;
import java.util.List;

public class UserAttributeGroupItem {
    private List<UserAttributeUserAttributeValue> userAttributeUserAttributeValues=new ArrayList<>();
    private UserAttributeGroup userAttributeGroup=new UserAttributeGroup();

    public void setUserAttributeGroupName(String name ){
        this.userAttributeGroup.setName(name);
    }

    public List<UserAttributeUserAttributeValue> getUserAttributeUserAttributeValues() {
        return userAttributeUserAttributeValues;
    }

    public String getUserAttributeGroupName() {
        return this.userAttributeGroup.getName();
    }

    public void addUserAttributeUserAttributeValue(UserAttributeUserAttributeValue userAttributeUserAttributeValue){
        userAttributeUserAttributeValues.add(userAttributeUserAttributeValue);
    }

}

class UserAttributeGroup {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}