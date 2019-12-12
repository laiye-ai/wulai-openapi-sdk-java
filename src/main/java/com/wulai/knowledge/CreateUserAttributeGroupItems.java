package com.wulai.knowledge;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.UserAttributeGroupItem;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class CreateUserAttributeGroupItems {
    private UserAttributeGroupItem userattributeGroupItem;

    public void setUserattributeGroupItem(UserAttributeGroupItem userattributeGroupItem) {
        this.userattributeGroupItem = userattributeGroupItem;
    }

    public UserAttributeGroupItem getUserattributeGroupItem() {
        return userattributeGroupItem;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_attribute_group_item", userattributeGroupItem);

        CloseableHttpResponse httpResponse = defaultClient.excuteRequest("/qa/user-attribute-group-items/create", params);

        return defaultClient.getEntityMapFromResponse(httpResponse);
    }

}

