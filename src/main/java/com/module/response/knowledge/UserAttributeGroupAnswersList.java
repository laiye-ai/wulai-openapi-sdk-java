package com.module.response.knowledge;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.knowledge.UserAttributeGroupAnswer;

import java.util.List;

public class UserAttributeGroupAnswersList {
    private List<UserAttributeGroupAnswer> user_attribute_group_answers;
    private int pageCount;

    @JSONField(name = "page_count")
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    @JSONField(name = "user_attribute_group_answers")
    public void setUser_attribute_group_answers(List<UserAttributeGroupAnswer> user_attribute_group_answers) {
        this.user_attribute_group_answers = user_attribute_group_answers;
    }

    @JSONField(name = "page_count")
    public int getPageCount() {
        return pageCount;
    }

    @JSONField(name = "user_attribute_group_answers")
    public List<UserAttributeGroupAnswer> getUser_attribute_group_answers() {
        return user_attribute_group_answers;
    }
}
