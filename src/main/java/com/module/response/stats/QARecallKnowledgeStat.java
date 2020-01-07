package com.module.response.stats;

import com.alibaba.fastjson.annotation.JSONField;

public class QARecallKnowledgeStat {
    private int knowledgeID;
    private String standardQuestion;
    private QARecallStats qaRecallStats;

    @JSONField(name = "knowledge_id")
    public int getKnowledgeID() {
        return knowledgeID;
    }

    @JSONField(name = "knowledge_id")
    public void setKnowledgeID(int value) {
        this.knowledgeID = value;
    }

    @JSONField(name = "standard_question")
    public String getStandardQuestion() {
        return standardQuestion;
    }

    @JSONField(name = "standard_question")
    public void setStandardQuestion(String value) {
        this.standardQuestion = value;
    }

    @JSONField(name = "qa_recall_stats")
    public QARecallStats getQARecallStats() {
        return qaRecallStats;
    }

    @JSONField(name = "qa_recall_stats")
    public void setQARecallStats(QARecallStats value) {
        this.qaRecallStats = value;
    }
}
