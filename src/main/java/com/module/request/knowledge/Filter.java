package com.module.request.knowledge;

public class Filter{
    String knowledge_id;
    String user_attribute_group_id;

    public void setKnowledge_id(String knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public String getKnowledge_id() {
        return knowledge_id;
    }

    public void setUser_attribute_group_id(String user_attribute_group_id) {
        this.user_attribute_group_id = user_attribute_group_id;
    }

    public String getUser_attribute_group_id() {
        return user_attribute_group_id;
    }
}