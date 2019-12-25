package com.module.request.knowledge;


import com.alibaba.fastjson.annotation.JSONField;

public class SimilarQuestion {
    private String knowledgeId;
    private String question;
    private String id;

    @JSONField(name = "knowledge_id")
    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getId() {
        return id;
    }

    @JSONField(name = "knowledge_id")
    public String getKnowledgeId() {
        return knowledgeId;
    }

    public String getQuestion() {
        return question;
    }
}