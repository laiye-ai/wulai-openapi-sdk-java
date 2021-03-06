package com.wulai.knowledge;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.UserAttributeGroupAnswer;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class CreateUserAttributeGroupAnswer {
    private UserAttributeGroupAnswer userAttributeGroupAnswer;

    public void setUserAttributeGroupAnswer(UserAttributeGroupAnswer userAttributeGroupAnswer) {
        this.userAttributeGroupAnswer = userAttributeGroupAnswer;
    }

    public UserAttributeGroupAnswer getUserAttributeGroupAnswer() {
        return userAttributeGroupAnswer;
    }

    public UserAttributeGroupAnswer request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();

        params.put("user_attribute_group_answer", userAttributeGroupAnswer);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/qa/user-attribute-group-answer/create", params);

        return wulaiClient.getResponse(httpResponse, UserAttributeGroupAnswer.class, "user_attribute_group_answer");

    }

}
