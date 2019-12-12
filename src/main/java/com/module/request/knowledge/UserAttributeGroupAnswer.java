package com.module.request.knowledge;

import com.module.request.knowledge.Answer;

public class UserAttributeGroupAnswer {
    private Answer answer;
    private String user_attribute_group_id;

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }

    public String getUser_attribute_group_id() {
        return user_attribute_group_id;
    }

    public void setUser_attribute_group_id(String user_attribute_group_id) {
        this.user_attribute_group_id = user_attribute_group_id;
    }


}
