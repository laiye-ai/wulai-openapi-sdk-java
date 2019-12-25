package com.module.response.stats;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class SatisfactionDailyKnowledgeList {

    private List<QARecallKnowledgeStat> qaRecallKnowledgeStats;
    private int pageCount;

    @JSONField(name = "qa_recall_knowledge_stats")
    public List<QARecallKnowledgeStat> getQARecallKnowledgeStats() {
        return qaRecallKnowledgeStats;
    }

    @JSONField(name = "qa_recall_knowledge_stats")
    public void setQARecallKnowledgeStats(List<QARecallKnowledgeStat> value) {
        this.qaRecallKnowledgeStats = value;
    }

    @JSONField(name = "page_count")
    public int getPageCount() {
        return pageCount;
    }

    @JSONField(name = "page_count")
    public void setPageCount(int value) {
        this.pageCount = value;
    }
}
