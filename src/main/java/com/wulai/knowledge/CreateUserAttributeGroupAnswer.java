package com.wulai.knowledge;

import com.DefaultClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.UserAttributeGroupAnswer;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class CreateUserAttributeGroupAnswer {
    private UserAttributeGroupAnswer userAttributeGroupAnswer;

    public void setUserAttributeGroupAnswer(UserAttributeGroupAnswer userAttributeGroupAnswer) {
        this.userAttributeGroupAnswer = userAttributeGroupAnswer;
    }

    public UserAttributeGroupAnswer getUserAttributeGroupAnswer() {
        return userAttributeGroupAnswer;
    }

    public Map request(DefaultClient defaultClient) throws ServerException, ClientException {
        HashMap<String,Object> params=new HashMap<>();

        params.put("user_attribute_group_answer",userAttributeGroupAnswer);

        CloseableHttpResponse closeableHttpResponse= defaultClient.excuteRequest("/qa/user-attribute-group-answer/create",params);

        return defaultClient.getEntityMapFromResponse(closeableHttpResponse);
    }

}
