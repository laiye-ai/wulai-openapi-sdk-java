package com.module.response.stats;

import com.alibaba.fastjson.annotation.JSONField;

public class QASatisfactionKnowledgeStat {

    private long knowledgeID;
    private SatisfactionStats satisfactionStats;
    private String standardQuestion;

    @JSONField( name ="knowledge_id")
    public long getKnowledgeID() { return knowledgeID; }
    @JSONField( name ="knowledge_id")
    public void setKnowledgeID(long value) { this.knowledgeID = value; }

    @JSONField( name ="satisfaction_stats")
    public SatisfactionStats getSatisfactionStats() { return satisfactionStats; }
    @JSONField( name ="satisfaction_stats")
    public void setSatisfactionStats(SatisfactionStats value) { this.satisfactionStats = value; }

    @JSONField( name ="standard_question")
    public String getStandardQuestion() { return standardQuestion; }
    @JSONField( name ="standard_question")
    public void setStandardQuestion(String value) { this.standardQuestion = value; }
}
