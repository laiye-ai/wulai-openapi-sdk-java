package com.module.request.user;

import com.exceptions.ClientException;
import com.util.ParamsCheck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAttributeCreateRequest  {

    private String user_id;

    private List<UserAttributeUserAttributeValue> user_attribute_user_attribute_values =
            new ArrayList<>();

    public UserAttributeCreateRequest(String user_id) throws ClientException {
        ParamsCheck.checkUserId(user_id);
        this.user_id = user_id;
    }

    public void setUser_id(String user_id) throws ClientException {
        ParamsCheck.checkUserId(user_id);
        this.user_id = user_id;
    }

    public List<UserAttributeUserAttributeValue> getUser_attribute_user_attribute_value() {
        return user_attribute_user_attribute_values;
    }

    public String getUser_id() {
        return user_id;
    }

    public void addUserAttributeUserAttributeValue(UserAttributeUserAttributeValue userAttributeUserAttributeValue) {
        this.user_attribute_user_attribute_values.add(userAttributeUserAttributeValue);
    }
}
