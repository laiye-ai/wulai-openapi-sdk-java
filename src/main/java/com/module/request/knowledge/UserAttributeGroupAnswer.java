package com.module.request.knowledge;

import com.alibaba.fastjson.annotation.JSONField;

public class UserAttributeGroupAnswer {
    private Answer answer;
    private String userAttributeGroupId;

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }

    @JSONField(name = "user_attribute_group_id")
    public String getUserAttributeGroupId() {
        return userAttributeGroupId;
    }

    @JSONField(name = "user_attribute_group_id")
    public void setUserAttributeGroupId(String userAttributeGroupId) {
        this.userAttributeGroupId = userAttributeGroupId;
    }


}
