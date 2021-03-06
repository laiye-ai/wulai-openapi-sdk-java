package com.wulai.knowledge;

import com.WulaiClient;
import com.exceptions.ClientException;
import com.exceptions.ServerException;
import com.module.request.knowledge.UserAttributeGroupAnswer;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.HashMap;

public class UpdateUserAttributeGroupAnswer {
    private UserAttributeGroupAnswer user_attribute_group_answer;


    public void setUser_attribute_group_answer(UserAttributeGroupAnswer user_attribute_group_answer) {
        this.user_attribute_group_answer = user_attribute_group_answer;
    }

    public UserAttributeGroupAnswer getUser_attribute_group_answer() {
        return user_attribute_group_answer;
    }

    public UserAttributeGroupAnswer request(WulaiClient wulaiClient) throws ServerException, ClientException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_attribute_group_answer", user_attribute_group_answer);

        CloseableHttpResponse httpResponse = wulaiClient.executeRequest("/qa/user-attribute-group-answer/update", params);
        return wulaiClient.getResponse(httpResponse, UserAttributeGroupAnswer.class, "user_attribute_group_answer");

    }


}
