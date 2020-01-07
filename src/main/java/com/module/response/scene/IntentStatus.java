package com.module.response.scene;


import com.alibaba.fastjson.annotation.JSONField;

public class IntentStatus {
    private boolean status;
    private long firstBlockID;
    private long intentID;
    private String updateTime;

    @JSONField(name= "status")
    public boolean getStatus() { return status; }
    @JSONField(name= "status")
    public void setStatus(boolean value) { this.status = value; }

    @JSONField(name= "first_block_id")
    public long getFirstBlockID() { return firstBlockID; }
    @JSONField(name= "first_block_id")
    public void setFirstBlockID(long value) { this.firstBlockID = value; }

    @JSONField(name= "intent_id")
    public long getIntentID() { return intentID; }
    @JSONField(name= "intent_id")
    public void setIntentID(long value) { this.intentID = value; }

    @JSONField(name= "update_time")
    public String getUpdateTime() { return updateTime; }
    @JSONField(name= "update_time")
    public void setUpdateTime(String value) { this.updateTime = value; }
}