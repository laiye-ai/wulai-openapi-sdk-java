package com.module.response.knowledge;

import com.alibaba.fastjson.annotation.JSONField;

public class KnowledgeTag {
    private String id;
    private String name;
    private String parentKnowledgeTagId;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    @JSONField(name = "parent_knowledge_tag_id")
    public void setParentKnowledgeTagId(String parentKnowledgeTagId) {
        this.parentKnowledgeTagId = parentKnowledgeTagId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JSONField(name = "parent_knowledge_tag_id")
    public String getParentKnowledgeTagId() {
        return parentKnowledgeTagId;
    }
}
