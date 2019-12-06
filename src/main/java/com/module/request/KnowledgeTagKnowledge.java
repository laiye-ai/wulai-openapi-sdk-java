package com.module.request;

public class KnowledgeTagKnowledge {
    private Knowledge knowledge;
    private String knowledgeTagId;

    public void setKnowledgeTagId(String knowledgeTagId) {
        this.knowledgeTagId = knowledgeTagId;
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }

    public String getKnowledgeTagId() {
        return knowledgeTagId;
    }
}
