package com.wulai.knowledge;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.UserAttributeGroupItem;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UpdateUserAttributeGroupItems {

    private UserAttributeGroupItem user_attribute_group_item;

    public void setUser_attribute_group_item(UserAttributeGroupItem user_attribute_group_item) {
        this.user_attribute_group_item = user_attribute_group_item;
    }

    public UserAttributeGroupItem getUser_attribute_group_item() {
        return user_attribute_group_item;
    }


    public UserAttributeGroupItem request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_attribute_group_item", user_attribute_group_item);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/qa/user-attribute-group-items/update", params);

        return defaultClient.getResponse(httpResponse,UserAttributeGroupItem.class,"user_attribute_group_item");
    }
}


