package com.module.response.stats;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class QASatisfactionKnowledgeStats {

    private int pageCount;
    private List<QASatisfactionKnowledgeStat> qaSatisfactionKnowledgeStats;

    @JSONField(name = "page_count")
    public int getPageCount() {
        return pageCount;
    }

    @JSONField(name = "page_count")
    public void setPageCount(int value) {
        this.pageCount = value;
    }

    @JSONField(name = "qa_satisfaction_knowledge_stats")
    public List<QASatisfactionKnowledgeStat> getQASatisfactionKnowledgeStats() {
        return qaSatisfactionKnowledgeStats;
    }

    @JSONField(name = "qa_satisfaction_knowledge_stats")
    public void setQASatisfactionKnowledgeStats(List<QASatisfactionKnowledgeStat> value) {
        this.qaSatisfactionKnowledgeStats = value;
    }
}
