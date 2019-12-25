package com.module.response.msg;

import com.alibaba.fastjson.annotation.JSONField;

public class QA {
    private int knowledgeId;
    private String standardQuestion;
    private String question;
    private boolean isNoneIntention;

    @JSONField(name = "is_none_intention")
    public void setNoneIntention(boolean noneIntention) {
        this.isNoneIntention = noneIntention;
    }

    @JSONField(name = "knowledge_id")
    public void setKnowledgeId(int knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @JSONField(name = "standard_question")
    public void setStandardQuestion(String standardQuestion) {
        this.standardQuestion = standardQuestion;
    }

    @JSONField(name = "knowledge_id")
    public int getKnowledgeId() {
        return knowledgeId;
    }

    public String getQuestion() {
        return question;
    }

    @JSONField(name = "standard_question")
    public String getStandardQuestion() {
        return standardQuestion;
    }

    @JSONField(name = "is_none_intention")
    public boolean isNoneIntention() {
        return isNoneIntention;
    }
}
