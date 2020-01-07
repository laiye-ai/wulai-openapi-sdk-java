package com.module.response.stats;

import com.alibaba.fastjson.annotation.JSONField;

public class QARecallStats {
    private int recallCount;

    @JSONField(name = "recall_count")
    public int getRecallCount() {
        return recallCount;
    }

    @JSONField(name = "recall_count")
    public void setRecallCount(int value) {
        this.recallCount = value;
    }
}
