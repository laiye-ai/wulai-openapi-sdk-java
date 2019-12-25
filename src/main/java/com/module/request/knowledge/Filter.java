package com.module.request.knowledge;

import com.alibaba.fastjson.annotation.JSONField;

public class Filter {
    private String knowledgeId;
    private String userAttributeGroupId;

    @JSONField(name = "knowledge_id")
    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    @JSONField(name = "knowledge_id")
    public String getKnowledgeId() {
        return knowledgeId;
    }

    @JSONField(name = "user_attribute_group_id")
    public void setUserAttributeGroupId(String userAttributeGroupId) {
        this.userAttributeGroupId = userAttributeGroupId;
    }

    @JSONField(name = "user_attribute_group_id")
    public String getUserAttributeGroupId() {
        return userAttributeGroupId;
    }
}