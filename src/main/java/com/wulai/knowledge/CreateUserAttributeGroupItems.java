package com.wulai.knowledge;

import com.DefaultClient;
import com.alibaba.fastjson.JSONObject;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.UserAttributeGroupItem;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateUserAttributeGroupItems {
    private UserAttributeGroupItem userattributeGroupItem;

    public void setUserattributeGroupItem(UserAttributeGroupItem userattributeGroupItem) {
        this.userattributeGroupItem = userattributeGroupItem;
    }

    public UserAttributeGroupItem getUserattributeGroupItem() {
        return userattributeGroupItem;
    }

    public UserAttributeGroupItem request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_attribute_group_item", userattributeGroupItem);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/qa/user-attribute-group-items/create", params);
        JSONObject response = defaultClient.getJsonFromResponse(httpResponse);
        return JSONObject.parseObject(response.get("user_attribute_group_item").toString(), UserAttributeGroupItem.class);

    }

}

