package com.module.response.nlp;

import com.alibaba.fastjson.annotation.JSONField;

public class UploadRes {
    private int succeededCount;
    private int failedCount;
    private int duplicatedCount;

    @JSONField(name ="succeeded_count")
    public int getSucceededCount() { return succeededCount; }
    @JSONField(name ="succeeded_count")
    public void setSucceededCount(int value) { this.succeededCount = value; }

    @JSONField(name ="failed_count")
    public int getFailedCount() { return failedCount; }
    @JSONField(name ="failed_count")
    public void setFailedCount(int value) { this.failedCount = value; }

    @JSONField(name ="duplicated_count")
    public int getDuplicatedCount() { return duplicatedCount; }
    @JSONField(name ="duplicated_count")
    public void setDuplicatedCount(int value) { this.duplicatedCount = value; }
}
