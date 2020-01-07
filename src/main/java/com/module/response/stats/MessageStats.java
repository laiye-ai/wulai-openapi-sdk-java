package com.module.response.stats;

import com.alibaba.fastjson.annotation.JSONField;

public class MessageStats {
    private int receiveCount;

    @JSONField(name = "receive_count")
    public int getReceiveCount() {
        return receiveCount;
    }

    @JSONField(name = "receive_count")
    public void setReceiveCount(int value) {
        this.receiveCount = value;
    }
}
