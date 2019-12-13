package com.module.request.scene;

public class Connection {
    private int from_block_id;
    private int to_block_id;
    private Condition condition;

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setFrom_block_id(int from_block_id) {
        this.from_block_id = from_block_id;
    }

    public void setTo_block_id(int to_block_id) {
        this.to_block_id = to_block_id;
    }

    public int getFrom_block_id() {
        return from_block_id;
    }

    public int getTo_block_id() {
        return to_block_id;
    }
}
