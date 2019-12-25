package com.module.response.stats;

import com.alibaba.fastjson.annotation.JSONField;

public class QARecallDailyStat {
    private String date;
    private MessageStats messageStats;
    private QARecallStats qaRecallStats;

    @JSONField(name = "date")
    public String getDate() {
        return date;
    }

    @JSONField(name = "date")
    public void setDate(String value) {
        this.date = value;
    }

    @JSONField(name = "message_stats")
    public MessageStats getMessageStats() {
        return messageStats;
    }

    @JSONField(name = "message_stats")
    public void setMessageStats(MessageStats value) {
        this.messageStats = value;
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
