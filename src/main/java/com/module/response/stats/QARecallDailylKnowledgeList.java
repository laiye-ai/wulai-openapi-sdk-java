package com.module.response.stats;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class QARecallDailylKnowledgeList {
    private List<QARecallKnowledgeStat> qaRecallKnowledgeStats;
    private long pageCount;

    @JSONField( name ="qa_recall_knowledge_stats")
    public List<QARecallKnowledgeStat> getQARecallKnowledgeStats() { return qaRecallKnowledgeStats; }
    @JSONField( name ="qa_recall_knowledge_stats")
    public void setQARecallKnowledgeStats(List<QARecallKnowledgeStat> value) { this.qaRecallKnowledgeStats = value; }

    @JSONField( name ="page_count")
    public long getPageCount() { return pageCount; }
    @JSONField( name ="page_count")
    public void setPageCount(long value) { this.pageCount = value; }
}
