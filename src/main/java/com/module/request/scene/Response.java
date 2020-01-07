package com.module.request.scene;

import com.alibaba.fastjson.annotation.JSONField;
import com.module.request.msg.MsgBody;

public class Response {
    private int id;
    private MsgBody response;
    private int blockId;

    @JSONField(name = "block_id")
    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    @JSONField(name = "block_id")
    public int getBlockId() {
        return blockId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setResponse(MsgBody response) {
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public MsgBody getResponse() {
        return response;
    }
}
