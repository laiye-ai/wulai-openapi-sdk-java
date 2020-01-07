package com.module.response.scene;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.scene.Condition;

public class Connection {
    private int fromBlockID;
    private int toBlockID;
    private Condition condition;

    @JSONField(name = "from_block_id")
    public int getFromBlockID() {
        return fromBlockID;
    }

    @JSONField(name = "from_block_id")
    public void setFromBlockID(int value) {
        this.fromBlockID = value;
    }

    @JSONField(name = "to_block_id")
    public int getToBlockID() {
        return toBlockID;
    }

    @JSONField(name = "to_block_id")
    public void setToBlockID(int value) {
        this.toBlockID = value;
    }

    @JSONField(name = "condition")
    public Condition getCondition() {
        return condition;
    }

    @JSONField(name = "condition")
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
