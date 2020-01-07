package com.wulai.knowledge;

import com.WulaiClient;
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

    public UserAttributeGroupItem request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_attribute_group_item", userattributeGroupItem);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/qa/user-attribute-group-items/create", params);
        return wulaiClient.getResponse(httpResponse,UserAttributeGroupItem.class,"user_attribute_group_item");


    }

}

