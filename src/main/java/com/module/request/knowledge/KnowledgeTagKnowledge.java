package com.module.request.knowledge;

import com.alibaba.fastjson.annotation.JSONField;

public class KnowledgeTagKnowledge {
    private Knowledge knowledge;
    private String knowledgeTagId;

    @JSONField(name = "knowledge_tag_id")
    public void setKnowledgeTagId(String knowledgeTagId) {
        this.knowledgeTagId = knowledgeTagId;
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }

    @JSONField(name = "knowledge_tag_id")
    public String getKnowledgeTagId() {
        return knowledgeTagId;
    }
}
